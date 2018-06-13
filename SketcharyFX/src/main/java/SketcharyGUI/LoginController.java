package SketcharyGUI;

import Factory.RoomOverviewControllerFactory;
import Logic.Authentication.IAuthentication;
import Logic.Authentication.invalidPasswordException;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    Button btnLogin;
    @FXML
    TextField tbUsername;
    @FXML
    PasswordField tbPassword;
    @FXML
    Label lbError;
    @FXML
    Button btnLoginGuest;

    IAuthentication authenthication;

    public LoginController(IAuthentication auth){
        authenthication = auth;
    }

    @FXML
    public void btnLoginGuest_OnClick(){
        try {
            User user = new User(1, "Guest1", "guest",2, 89);

                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SketchyRoomOverview.fxml"));
                //TODO: naam aanpasse sketchy -- controller
                SketcharyRoomOverviewController roomController = RoomOverviewControllerFactory.sketchyController(user);
                loader.setController(roomController);
                Parent root = loader.load();
                //Open and show the new homepageMember window
                Stage stage = new Stage();
                stage.setTitle("Sketchary kameroverzicht");
                stage.setScene(new Scene(root, 1121, 839));

                stage.show();


                //Close the current window
                Stage thisStage = (Stage) btnLogin.getScene().getWindow();
                thisStage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnLogin_OnClick(){
        try {
            String username = tbUsername.getText();
            String password = tbPassword.getText();
            User user = new User();
            try{
                user = authenthication.signIn(username,password);

                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SketchyRoomOverview.fxml"));
                //TODO: naam aanpasse sketchy -- controller
                SketcharyRoomOverviewController roomController = RoomOverviewControllerFactory.sketchyController(user);
                loader.setController(roomController);
                Parent root = loader.load();
                //Open and show the new homepageMember window
                Stage stage = new Stage();
                stage.setTitle("Sketchary kameroverzicht");
                stage.setScene(new Scene(root, 1121, 839));

                stage.show();


                //Close the current window
                Stage thisStage = (Stage) btnLogin.getScene().getWindow();
                thisStage.close();

            } catch (invalidPasswordException e) {
                lbError.setText(e.toString());
                e.printStackTrace();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
