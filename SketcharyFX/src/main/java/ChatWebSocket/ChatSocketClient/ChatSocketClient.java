package ChatWebSocket.ChatSocketClient;


import ChatWebSocket.ChatSocketShared.ChatSocketMessage;
import ChatWebSocket.ChatSocketShared.ChatSocketMessageOperation;
import com.google.gson.Gson;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ChatSocketClient extends Chatter {

    private static ChatSocketClient instance = null;

    private final String uri = "ws://localhost:8095/chat/";
    private Session session;
    private String message;
    private Gson gson = null;
    // Status of the webSocket client
    boolean isRunning = false;

    private ChatSocketClient(){
        gson = new Gson();
    }

    public static ChatSocketClient getInstance() {
        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new ChatSocketClient();
        }
        return instance;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            startClient();
        }
    }

    private void startClient() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        } catch (Exception ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            stopClient();
        }
    }

    private void stopClient() {
        try {
            session.close();

        } catch (IOException ex){
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        this.session = session;
        System.out.println("Creating connection Client");
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        this.message = message;
        processMessage(message);
    }

    private void processMessage(String jsonMsg) {
        ChatSocketMessage chatMsg;
        try{
            chatMsg = gson.fromJson(jsonMsg, ChatSocketMessage.class);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return;
        }

        ChatSocketMessageOperation operation;
        operation = chatMsg.getOperation();
        if (operation == null || operation != ChatSocketMessageOperation.UPDATE) {
            System.out.println("[WebSocket Client ERROR: update property operation expected]");
            return;
        }

        // Obtain property from message
        //TODO: property verwijderen, kijken of user niet null is..
        //TODO: eventproperty mag miss null zijn..
        String eventProperty = chatMsg.getEventProperty();
        String userProperty = chatMsg.getUserProperty();

        // Obtain content from message
        String content = chatMsg.getContent();

        if (content == null || "".equals(content)) {
            System.out.println("[WebSocket Client ERROR: message without content]");
            return;
        }

        // Create instance of CommunicaterMessage for observers
        ChatMessage commMessage = new ChatMessage();
        commMessage.setEventProperty(eventProperty);
        commMessage.setUserProperty(userProperty);
        commMessage.setContent(content);

        // Notify observers
        this.setChanged();
        this.notifyObservers(commMessage);
    }

    public void register(String userProperty, String eventProperty) {
        ChatSocketMessage message = new ChatSocketMessage();
        message.setOperation(ChatSocketMessageOperation.REGISTER);
        message.setUserProperty(userProperty);
        message.setEventProperty(eventProperty);
        sendMessageToServer(message);
    }

    private void sendMessageToServer(ChatSocketMessage message) {
        String jsonMessage = gson.toJson(message);
        // Use asynchronous communication
        session.getAsyncRemote().sendText(jsonMessage);
    }

    public void subscribe(String userProperty, String eventProperty) {
        ChatSocketMessage message = new ChatSocketMessage();
        message.setOperation(ChatSocketMessageOperation.SUBSCRIBEEVENT);
        message.setUserProperty(userProperty);
        message.setEventProperty(eventProperty);
        sendMessageToServer(message);
    }

    public void unsubscribe(String eventProperty, String userProperty) {
        ChatSocketMessage message = new ChatSocketMessage();
        message.setOperation(ChatSocketMessageOperation.UNSUBSCRIBEEVENT);
        message.setUserProperty(userProperty);
        message.setEventProperty(eventProperty);
        sendMessageToServer(message);
    }

    @Override
    public void unregister(String userProperty, String eventProperty) {

    }

    public void unregister(String userProperty) {
        ChatSocketMessage message = new ChatSocketMessage();
        message.setOperation(ChatSocketMessageOperation.UNREGISTER);
        message.setUserProperty(userProperty);
        sendMessageToServer(message);
    }

    public void update(ChatMessage msg) {
        ChatSocketMessage chatMsg = new ChatSocketMessage();
        chatMsg.setOperation(ChatSocketMessageOperation.UPDATE);
        chatMsg.setUserProperty(msg.getUserProperty());
        chatMsg.setEventProperty(msg.getEventProperty());
        chatMsg.setContent(msg.getContent());
        sendMessageToServer(chatMsg);
    }
}
