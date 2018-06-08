package rpsclientapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rpsclient.*;
import rpsclient.messageHandlers.ClientMessageHandlerFactory;
import rpsshared.Logging.Logger;
import rpsshared.interfaces.IMessageHandlerFactory;

import java.lang.reflect.Constructor;

public class RockPaperScissors
        extends Application   {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RpsGUI.fxml"));

        IClientWebSocket socket = new ClientWebSocket();
        IClientMessageGenerator generator = new ClientMessageGenerator(socket);

        IGameClient gameClient = new GameClient(generator);
        IMessageHandlerFactory factory = new ClientMessageHandlerFactory();

        IClientMessageProcessor handler = new ClientMessageProcessor(factory);
        socket.setMessageHandler(handler);

        socket.start();

        handler.registerGameClient(gameClient);



        /*
                USE REFLECTION FOR DEPENDENCY INJECTION:
                INJECT EVERY CONTROLLER INSTANCE, WHICH IS CREATED DYNAMICALLY WHILE LOADING THE FXML,
                WITH THE SAME GAME CLIENT INSTANCE
         */
        loader.setControllerFactory((Class<?> type) -> {
            try {
                // look for constructor taking MyService as a parameter
                for (Constructor<?> c : type.getConstructors()) {
                    if (c.getParameterCount() == 1 && c.getParameterTypes()[0]==IGameClient.class) {
                            return c.newInstance(gameClient);
                    }
                }
                // didn't find appropriate constructor, just use default constructor:
                return type.newInstance();
            } catch (Exception exc) {
                Logger.getInstance().log(exc);
                return null;
            }
        });


        Parent root = loader.load();
        primaryStage.setTitle("Rock Paper Scissors");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
