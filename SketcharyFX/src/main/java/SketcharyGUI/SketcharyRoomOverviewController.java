package SketcharyGUI;

import ChatWebSocket.ChatSocketClient.Chatter;
import DrawWebSocket.DrawSocketClient.Drawer;
import Factory.SketchyControllerFactory;
import Models.Room;
import Models.User;
import PlayersWebSocket.PlayerSocketClient.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class SketcharyRoomOverviewController {

    User user;

    public SketcharyRoomOverviewController(User user) {
        this.user = user;
    }

    @FXML
    Button btnSelectRoom;
    @FXML
    ListView lvRooms;
    @FXML
    Label lbUser;

    @FXML
    protected void initialize(){
        lvRooms.getItems().add("Kaaskoppen");
        lvRooms.getItems().add("Molenwiek");
        lvRooms.getItems().add("Gouden Klompen");

        lbUser.setText(user.getUsername());
    }

    @FXML
    private void btnSelectRoom_OnClick(){

        String roomName = lvRooms.getSelectionModel().getSelectedItem().toString();

        Room room = new Room();
        room.setRoomName(roomName);


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SketcharyGui.fxml"));
            //TODO: naam aanpassen sketchy -- controller
            Controller controller = SketchyControllerFactory.sketchyController(user, room);
            loader.setController(controller);
            Parent root = loader.load();
            //Open and show the new homepageMember window
            Stage stage = new Stage();
            stage.setTitle("Sketchary");
            stage.setScene(new Scene(root));
            stage.show();


            //Close the current window
            Stage thisStage = (Stage) btnSelectRoom.getScene().getWindow();
            thisStage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    Drawer drawer = null;
    Player player = null;
    Chatter chatter = null;

    private void createSocketConnection(){

    }

}
