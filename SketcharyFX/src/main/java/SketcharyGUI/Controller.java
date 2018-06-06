package SketcharyGUI;

import DrawWebSocket.DrawSocketClient.DrawMessage;
import DrawWebSocket.DrawSocketClient.DrawSocketClient;
import DrawWebSocket.DrawSocketClient.Drawer;
import Factory.UserFactory;
import Logic.IUserLogic;
import Models.DrawEvent;
import Models.User;
import Rest.RestClient.Config;
import Rest.RestClient.clientResource.client.SketcharyGetsketcharyIdClientResource;
import SketcharyLogic.WhiteboardHandler;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;


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


    WhiteboardHandler whiteboardHandler = new WhiteboardHandler();
    Drawer drawer = null;
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

    @FXML
    private void btnTest_OnClick(ActionEvent event){
        IUserLogic userLogic = UserFactory.ManageUsers();

        for (User u :
                userLogic.getAllUsers()) {
            lvGameParticipants.getItems().add(u);
        }

        Config config = new Config();
        config.setBasePath("http://localhost:9001/v1");

        SketcharyGetsketcharyIdClientResource getSketchy = new SketcharyGetsketcharyIdClientResource(config, "1");
        String sketch = getSketchy.getsasketchary().getSketchary();

        System.out.println(sketch);

    }

    @FXML
    private void btnLogin_OnClick(ActionEvent event){
        // Create the client web socket to communicate with other white boards
        drawer = DrawSocketClient.getInstance();
        drawer.addObserver(this);

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

    @FXML
    private void btnSubscribe_OnClick(ActionEvent event){
        drawer.subscribe("black");
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
        System.out.println("Updateee");
        DrawMessage message = (DrawMessage) arg;
        String property = message.getProperty();
        String content = message.getContent();
        DrawEvent drawEvent = gson.fromJson(content, DrawEvent.class);
        requestDrawDot(property, drawEvent);
    }

    public void requestDrawDot(String property, DrawEvent drawEvent){
        System.out.println("Requestt");
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
