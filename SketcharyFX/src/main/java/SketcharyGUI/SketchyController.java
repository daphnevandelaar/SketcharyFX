package SketcharyGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class SketchyController {

    @FXML
    Button btnStop;
    @FXML
    Button btnStart;

    @FXML
    private void btnStart_OnClick(){

    }

    @FXML
    private void btnStop_OnClick(){
        //TODO: Implement new random sketchy getter to other user

        Stage stage = (Stage) btnStop.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
