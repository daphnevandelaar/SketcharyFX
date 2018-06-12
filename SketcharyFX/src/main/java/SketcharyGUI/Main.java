package SketcharyGUI;

import Factory.LoginControllerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SketcharyLogin.fxml"));
            loader.setController(LoginControllerFactory.loginController());
            Parent root = loader.load();
            //Open and show the new homepageMember window

            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 1121, 839));
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
