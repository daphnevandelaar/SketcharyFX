package rpsclientapp.controllers;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.enums.Choice;
import rpsclient.GameClient;
import rpsclient.IGameClient;
import rpsshared.interfaces.IClientGUI;

import java.util.TimerTask;

public class RpsController extends BaseController implements IClientGUI {

    public RpsController(IGameClient gameClient){
        super(gameClient);
        getGameClient().registerClientGUI(this);
        setIsRegistrationVisible(true);
        setIsGameVisible(false);
        setCanRegister(true);
        setCanEnterRoundInput(false);
        setIsRoundResultVisible(false);
        setResponseText("Waiting for registration");
        setRoundInputText("Waiting for round input");
        setRoundResultText("");
    }

    //AUTOMATED DEPENDENCY INJECTION
    @FXML
    private TextField usernameField;

    public void register()
    {
        String userName = usernameField.getText();
        if(userName.equals(""))
        {
            setResponseText("Invalid user name");
        }
        else {
            setRegisteredUserText("Current user: " + userName);
            setCanRegister(false);
            setResponseText("Checking user");
            getGameClient().registerPlayer(userName);
        }
    }

    public void processRegistrationResponse(boolean resp)
    {
        Platform.runLater(() -> {
            if(resp)
            {
                setIsRegistrationVisible(false);
                setResponseText("Registration success, waiting for round to start");
            }
            else
            {
                setCanRegister(true);
                setResponseText("Registration failed, please try again");
            }
        });
    }

    public void processRoundStarted()
    {
        Platform.runLater(() -> {
            setResponseText("Round starting in 5 seconds");
            new java.util.Timer().schedule(new TimerTask(){
                @Override
                public void run() {
                    startRound();
                }
            },5000);
        });
    }

    private void startRound()
    {
        Platform.runLater(() -> {
            setIsRoundResultVisible(false);
            setIsGameVisible(true);
            setCanEnterRoundInput(true);
            setResponseText("Round active");

        });
    }

    public void processRoundInputRock()
    {
                setCanEnterRoundInput(false);
                setResponseText("You chose Rock, Waiting for other player choice");
                getGameClient().sendPlayerChoice(Choice.ROCK);
    }

    public void processRoundInputPaper()
    {
        setCanEnterRoundInput(false);
        setResponseText("You chose Paper, Waiting for other player choice");
        getGameClient().sendPlayerChoice(Choice.PAPER);
    }

    public void processRoundInputScissors()
    {
        setCanEnterRoundInput(false);
        setResponseText("You chose Scissors, Waiting for other player choice");
        getGameClient().sendPlayerChoice(Choice.SCISSORS);
    }

    public void processPlayerRegistered(String userName)
    {
        Platform.runLater(() -> showAlert("Player has registered", "name: " + userName));
    }

    private void showAlert(String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void processRoundDraw()
    {
        Platform.runLater(() -> {
            setIsGameVisible(false);
            setIsRoundResultVisible(true);
            setRoundResultText("Round draw!");
        });

    }

    public void processRoundResult(String winner, String loser)
    {
        Platform.runLater(() -> {
            setIsGameVisible(false);
            setIsRoundResultVisible(true);
            setRoundResultText("Winner: " + winner + " loser: " + loser);
        });

    }

    //Set of properties for visibility
    private BooleanProperty isRegistrationVisible = new SimpleBooleanProperty();
    public final boolean getIsRegistrationVisible(){return isRegistrationVisible.get();}
    public final void setIsRegistrationVisible(boolean value){isRegistrationVisible.set(value);}
    public BooleanProperty isRegistrationVisibleProperty() {return isRegistrationVisible;}


    private BooleanProperty isGameVisible = new SimpleBooleanProperty();
    public final boolean getIsGameVisible(){return isGameVisible.get();}
    public final void setIsGameVisible(boolean value){isGameVisible.set(value);}
    public BooleanProperty isGameVisibleProperty() {return isGameVisible;}


    private BooleanProperty isRoundResultVisible = new SimpleBooleanProperty();
    public final boolean getIsRoundResultVisible(){return isRoundResultVisible.get();}
    public final void setIsRoundResultVisible(boolean value){isRoundResultVisible.set(value);}
    public BooleanProperty isRoundResultVisibleProperty() {return isRoundResultVisible;}


    private BooleanProperty canRegister = new SimpleBooleanProperty();
    public final boolean getCanRegister(){return canRegister.get();}
    public final void setCanRegister(boolean value){canRegister.set(value);}
    public BooleanProperty canRegisterProperty() {return canRegister;}

    private BooleanProperty canEnterRoundInput= new SimpleBooleanProperty();
    public final boolean getCanEnterRoundInput(){return canEnterRoundInput.get();}
    public final void setCanEnterRoundInput(boolean value){canEnterRoundInput.set(value);}
    public BooleanProperty canEnterRoundInputProperty() {return canEnterRoundInput;}


    private StringProperty responseText = new SimpleStringProperty();
    public final String getResponseText() { return responseText.get();}
    public final void setResponseText(String value){ responseText.set(value); }
    public StringProperty responseTextProperty() {
        return responseText;
    }

    private StringProperty registeredUserText = new SimpleStringProperty();
    public final String getRegisteredUserText() { return registeredUserText.get();}
    public final void setRegisteredUserText(String value){ registeredUserText.set(value); }
    public StringProperty registeredUserTextProperty() {
        return registeredUserText;
    }

    private StringProperty roundInputText = new SimpleStringProperty();
    public final String getRoundInputText() { return roundInputText.get();}
    public final void setRoundInputText(String value){
        roundInputText.set(value);
    }
    public StringProperty roundInputProperty() {
        return roundInputText;
    }

    private StringProperty roundResultText = new SimpleStringProperty();
    public final String getRoundResultText() { return roundResultText.get();}
    public final void setRoundResultText(String value){ roundResultText.set(value); }
    public StringProperty roundResultTextProperty() {
        return roundResultText;
    }

}
