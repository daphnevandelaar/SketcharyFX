package SketcharyGUI;

import Factory.SketchyControllerFactory;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    Button btnLogin;
    @FXML
    TextField tbUsername;
    @FXML
    TextField tbPassword;

    @FXML
    public void btnLogin_OnClick(){
        try {
            String username = tbUsername.getText();
            String password = tbPassword.getText();
            User user = new User();
            user.setUsername(username);
            if(user == null){
                //todo show error message that login in failed
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SketcharyGui.fxml"));
            //TODO: naam aanpasse sketchy -- controller
            Controller controller = SketchyControllerFactory.sketchyController(user);
            loader.setController(controller);
            Parent root = loader.load();
            //Open and show the new homepageMember window
            Stage stage = new Stage();
            stage.setTitle("Homepage for Members");
            stage.setScene(new Scene(root));
            stage.show();


            //Close the current window
            Stage thisStage = (Stage) btnLogin.getScene().getWindow();
            thisStage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
