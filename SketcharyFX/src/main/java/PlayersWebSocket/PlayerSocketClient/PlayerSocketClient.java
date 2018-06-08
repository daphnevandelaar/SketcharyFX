package PlayersWebSocket.PlayerSocketClient;

import PlayersWebSocket.PlayerSocketShared.PlayerSocketMessage;
import PlayersWebSocket.PlayerSocketShared.PlayerSocketOperation;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class PlayerSocketClient extends Player {

    private static PlayerSocketClient instance = null;

    private final String uri = "ws://localhost:8096/player/";
    private Session session;
    private String message;
    private Gson gson = null;
    //status of client
    boolean isRunning = false;

    private PlayerSocketClient(){gson = new Gson();}

    public static PlayerSocketClient getIntance(){
        if(instance == null){
            System.out.println("[PlayerSocket Client instance created]");
            instance = new PlayerSocketClient();
        }
        return instance;
    }

    @OnOpen
    public void onPlayerSocketConnection(Session session){
        System.out.println("[PlayerSocket Client open session] "+ session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onPlayerSocketText(String message, Session session){
        this.message = message;
        System.out.println("[PlayerSocket Client message received ]" + message);
        processMessage(message);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println("[PlayerSocket Client connection error] " + cause.toString());
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        System.out.print("[WebSocket Client close session] " + session.getRequestURI());
        System.out.println(" for reason " + reason);
        session = null;
    }

    private void processMessage(String jsonMsg) {
        System.out.println("Processing message "+jsonMsg);

        PlayerSocketMessage playerSocketMessage = new PlayerSocketMessage();
        try{
            playerSocketMessage = gson.fromJson(jsonMsg, PlayerSocketMessage.class);
        }
        catch (JsonSyntaxException jsnEx){
            System.out.println("[PlayerSocket Client ERROR: cannot parse Json message " + jsnEx);
        }

        PlayerSocketOperation operation;
        operation = playerSocketMessage.getOperation();
        if(operation == null || operation != PlayerSocketOperation.UPDATE){
            System.out.println("[PlayerSocket Client ERROR: update user expected]");
            return;
        }

        String property = playerSocketMessage.getProperty();
        if (property == null || "".equals(property)) {
            System.out.println("[PlayerSocket Client ERROR: property not defined]");
            return;
        }

        String content = playerSocketMessage.getContent();
        if (content == null || "".equals(content)){
            System.out.println("[PlayerSocket Client ERROR: message without content");
            return;
        }

        PlayerMessage commMessage = new PlayerMessage();
        commMessage.setProperty(property);
        commMessage.setContent(content);

        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void startConnection(){
        System.out.printf("[PlayerSocket Client connection started]");
        if(!isRunning){
            isRunning = true;
            startClient();
        }
    }

    @Override
    public void login(String property) {
        PlayerMessage message = new PlayerMessage();
        message.setOperation(PlayerSocketOperation.ONLINE);
        message.setProperty(property);
        sendMessageToServer(message);
    }

    private void sendMessageToServer(PlayerMessage message) {
        String jsonMsg = gson.toJson(message);
        session.getAsyncRemote().sendText(jsonMsg);
    }

    @Override
    public void logout(String property) {
        PlayerMessage message = new PlayerMessage();
        message.setOperation(PlayerSocketOperation.OFFLINE);
        message.setProperty(property);
        sendMessageToServer(message);
    }

    @Override
    public void stopConnection() {
        System.out.println("[WebSocket Client stop]");
        if (isRunning) {
            isRunning = false;
            stopClient();
        }
    }

    private void stopClient() {
        System.out.println("[WebSocket Client stop]");
        try {
            session.close();
        } catch (IOException ex){
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    @Override
    public void update(PlayerMessage playerMessage) {
        PlayerSocketMessage playerSocketMessage = new PlayerSocketMessage();
        playerSocketMessage.setOperation(PlayerSocketOperation.UPDATE);
        playerSocketMessage.setProperty(playerMessage.getProperty());
        playerSocketMessage.setContent(playerMessage.getContent());
        System.out.println("Update with: " + playerMessage + " and PlayerSocket message: "+ playerSocketMessage);
        sendMessageToServer(playerMessage);
    }

    private void startClient() {
        System.out.println("[PlayerSocket Client start]");
        try{
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
