package ChatWebSocket.ChatSocketClient;


import ChatWebSocket.ChatSocketShared.ChatSocketMessage;
import ChatWebSocket.ChatSocketShared.ChatSocketMessageOperation;
import Sockets.SocketMessageIdentifier;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ChatSocketClient extends Chatter {

    private static ChatSocketClient instance = null;

    private final String uri = "ws://localhost:8097/chat/";
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
            System.out.println("[Chatsocket Client create singleton instance]");
            instance = new ChatSocketClient();
        }
        return instance;
    }

    public void start() {
        System.out.println("[Chatsocket Client start connection]");
        if (!isRunning) {
            isRunning = true;
            startClient();
        }
    }

    private void startClient() {
        System.out.println("[Chatsocket Client start]");
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
        System.out.println("[Drawsocket Client open session] " + session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        this.message = message;
        System.out.println("[Drawsocket Client message received] " + message);
        processMessage(message);
    }

    private void processMessage(String jsonMsg) {
        // Parse incoming message
        ChatSocketMessage wsMessage;
        try {
            wsMessage = gson.fromJson(jsonMsg, ChatSocketMessage.class);
        }
        catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket Client ERROR: cannot parse Json message " + jsonMsg);
            return;
        }

        // Only operation update property will be further processed
        ChatSocketMessageOperation operation;
        operation = wsMessage.getOperation();
        if (operation == null || operation != ChatSocketMessageOperation.UPDATE) {
            System.out.println("[WebSocket Client ERROR: update property operation expected]");
            return;
        }

        // Obtain property from message
        String userProperty = wsMessage.getUserProperty();
        if (userProperty == null || "".equals(userProperty)) {
            System.out.println("[WebSocket Client ERROR: property not defined]");
            return;
        }

        // Obtain property from message
        String eventProperty = wsMessage.getEventProperty();
        if (eventProperty == null || "".equals(eventProperty)) {
            System.out.println("[WebSocket Client ERROR: property not defined]");
            return;
        }

        // Obtain content from message
        String content = wsMessage.getContent();
        if (content == null || "".equals(content)) {
            System.out.println("[WebSocket Client ERROR: message without content]");
            return;
        }

        // Create instance of CommunicaterMessage for observers
        ChatMessage commMessage = new ChatMessage();
        commMessage.setUserProperty(userProperty);
        commMessage.setEventProperty(eventProperty);
        commMessage.setContent(content);
        commMessage.setIdentifier(SocketMessageIdentifier.CHATMESSAGE);

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
        ChatSocketMessage message = new ChatSocketMessage();
        message.setOperation(ChatSocketMessageOperation.UNREGISTER);
        message.setUserProperty(userProperty);
        sendMessageToServer(message);
    }

    @Override
    public void update(ChatMessage msg) {
        ChatSocketMessage chatMsg = new ChatSocketMessage();
        chatMsg.setOperation(ChatSocketMessageOperation.UPDATE);
        chatMsg.setUserProperty(msg.getUserProperty());
        chatMsg.setEventProperty(msg.getEventProperty());
        chatMsg.setContent(msg.getContent());
        sendMessageToServer(chatMsg);
    }
}
