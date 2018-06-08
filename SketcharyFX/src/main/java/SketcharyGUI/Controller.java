package SketcharyGUI;

import DrawWebSocket.DrawSocketClient.DrawMessage;
import DrawWebSocket.DrawSocketClient.DrawSocketClient;
import DrawWebSocket.DrawSocketClient.Drawer;
import Factory.SketcharyFactory;
import Factory.UserFactory;
import Logic.GameLogic;
import Logic.ISketcharyLogic;
import Logic.IUserLogic;
import Models.DrawEvent;
import Models.Game;
import Models.Sketchary;
import Models.User;
import PlayersWebSocket.PlayerSocketClient.Player;
import PlayersWebSocket.PlayerSocketClient.PlayerMessage;
import PlayersWebSocket.PlayerSocketClient.PlayerSocketClient;
import SketcharyLogic.WhiteboardHandler;
import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;


public class Controller implements Observer {

    @FXML
    private ListView lvGameParticipants;
    @FXML
    private Canvas cvWhiteboard;
    @FXML
    private VBox vboxChat;
    @FXML
    private TextField tbMessage;
    @FXML
    private TextField tbUsername;
    @FXML
    private Button btnTest;
    @FXML
    private Label lbTimer;

    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(60);

    WhiteboardHandler whiteboardHandler = new WhiteboardHandler();
    Drawer drawer = null;
    Player player = null;

    private String currentProperty = "black";

//    //To change guesser and sketcher
//    @FXML
//    private void btnTest_OnClick(ActionEvent event){
//        cvWhiteboard.setDisable(true);
//        System.out.println("Geklikt");
//    }
//    @FXML
//    private void btnTestt_OnClick(ActionEvent event){
//        cvWhiteboard.setDisable(false);
//        System.out.println("Gekliktttt");
//    }

    private void startGame(){
        lbTimer.textProperty().bind(timeSeconds.asString());
        timeSeconds.set(3);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(3+1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();

        timeline.setOnFinished((ActionEvent t) -> {
            //TODO: implement einde van de game hier
            throw new NotImplementedException();
        });
    }

    private void openSketchy(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SketchyPopUp.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Sketchy");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception ex ){
            System.out.println(ex);
        }
    }

    @FXML
    private void btnTest_OnClick(ActionEvent event){

        GameLogic gameLogic = new GameLogic();
        Game game = gameLogic.startGame();

        System.out.println(game.getSketcher());
        System.out.println(game.getSketchy());


    }

    @FXML
    private void btnLogin_OnClick(ActionEvent event){
        // Create the client web socket to communicate with other white boards
        drawer = DrawSocketClient.getInstance();
        drawer.addObserver(this);

        player = PlayerSocketClient.getIntance();
        player.addObserver(this);

        player.startConnection();
        player.login("Daf");

        // Establish connection with server
        drawer.start();
        drawer.register("black");
        drawer.subscribe("black");
        //TODO: Update?
        //TODO: Disable controls to force login
        //TODO: Enable controls after login
        //TODO: ifstatement when no text in textbox
        if(!tbUsername.getText().equals("")){
            User user = new User(tbUsername.getText());
            lvGameParticipants.getItems().add(user);
        }
        else{
            User user = new User("GUEST");
            lvGameParticipants.getItems().add(user);
        }
    }

    private void drawDot(Color color, double xPos, double yPos) {
        // Graphics
        GraphicsContext gc = cvWhiteboard.getGraphicsContext2D();
        // Set color
        gc.setFill(color);
        // Draw dot
        gc.fillOval(xPos - 2, yPos - 2, 10, 10);

    }

    @FXML
    private void btnSend_OnClick(ActionEvent event){
        vboxChat.getChildren().add(new Text(tbMessage.getText()));
        tbMessage.setText("");
    }

    @FXML
    private void cvWhiteboard_OnMouseDrag(MouseEvent event) {
        drawDot(Color.BLACK, event.getX(),event.getY());
        broadcastDrawedLine(currentProperty,event.getX(),event.getY());
    }


    private Gson gson = new Gson();

    public void broadcastDrawedLine(String property, double X, double Y){
        if(drawer != null){
            DrawEvent drawEvent = new DrawEvent(X, Y);
            String content = gson.toJson(drawEvent);
            DrawMessage message = new DrawMessage();
            message.setProperty(property);
            message.setContent(content);
            //System.out.println(message);
            drawer.update(message);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        DrawMessage message = (DrawMessage) arg;

        String property = message.getProperty();
        String content = message.getContent();

        DrawEvent drawEvent = gson.fromJson(content, DrawEvent.class);

        requestDrawDot(property, drawEvent);
    }
    public void requestAddPlayer(String property, Player player){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvGameParticipants.getItems().add(player);
            }
        });
    }

    public void requestDrawDot(String property, DrawEvent drawEvent){
        final Color color = Color.BLACK;
        final double xPos = drawEvent.getXpos();
        final double yPos = drawEvent.getYpos();
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                drawDot(Color.BLACK, xPos, yPos);
            }
        });
    }


}
