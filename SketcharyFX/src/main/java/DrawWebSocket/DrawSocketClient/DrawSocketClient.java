package DrawWebSocket.DrawSocketClient;

import DrawWebSocket.DrawShared.DrawSocketMessage;
import DrawWebSocket.DrawShared.DrawSocketMessageOperation;
import Sockets.SocketMessageIdentifier;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class DrawSocketClient extends Drawer {

    // Singleton
    private static DrawSocketClient instance = null;

    /**
     * The local websocket uri to connect to.
     */
    private final String uri = "ws://localhost:8095/drawer/";
    private Session session;
    private String message;
    private Gson gson = null;
    // Status of the webSocket client
    boolean isRunning = false;

    // Private constructor (singleton pattern)
    private DrawSocketClient() {
        gson = new Gson();
    }

    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     * @return instance of client web socket
     */
    public static DrawSocketClient getInstance() {
        if (instance == null) {
            System.out.println("[Drawsocket Client create singleton instance]");
            instance = new DrawSocketClient();
        }
        return instance;
    }

    /**
     *  Start the connection.
     */
    public void start() {
        System.out.println("[Drawsocket Client start connection]");
        if (!isRunning) {
            isRunning = true;
            startClient();
        }
    }

    public void stop() {
        System.out.println("[WebSocket Client stop]");
        if (isRunning) {
            isRunning = false;
            stopClient();
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

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println("[Drawsocket Client connection error] " + cause.toString());
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        System.out.print("[Drawsocket Client close session] " + session.getRequestURI());
        System.out.println(" for reason " + reason);
        session = null;
    }

    public void register(String property) {
        DrawSocketMessage message = new DrawSocketMessage();
        message.setOperation(DrawSocketMessageOperation.REGISTERPROPERTY);
        message.setProperty(property);
        sendMessageToServer(message);
    }

    public void unregister(String property) {
        DrawSocketMessage message = new DrawSocketMessage();
        message.setOperation(DrawSocketMessageOperation.UNREGISTERPROPERTY);
        message.setProperty(property);
        sendMessageToServer(message);
    }

    public void subscribe(String property) {
        DrawSocketMessage message = new DrawSocketMessage();
        message.setOperation(DrawSocketMessageOperation.SUBSCRIBETOPROPERTY);
        message.setProperty(property);
        sendMessageToServer(message);
    }

    public void unsubscribe(String property) {
        DrawSocketMessage message = new DrawSocketMessage();
        message.setOperation(DrawSocketMessageOperation.UNSUBSCRIBEFROMPROPERTY);
        message.setProperty(property);
        sendMessageToServer(message);
    }

    public void update(DrawMessage message) {
        DrawSocketMessage wsMessage = new DrawSocketMessage();
        wsMessage.setOperation(DrawSocketMessageOperation.UPDATEPROPERTY);
        wsMessage.setProperty(message.getProperty());
        wsMessage.setContent(message.getContent());
        sendMessageToServer(wsMessage);
    }

    private void sendMessageToServer(DrawSocketMessage message) {
        String jsonMessage = gson.toJson(message);
        // Use asynchronous communication
        session.getAsyncRemote().sendText(jsonMessage);
    }

    /**
     * Get the latest message received from the websocket communication.
     * @return The message from the websocket communication
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message, but no action is taken when the message is changed.
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Start a WebSocket client.
     */
    private void startClient() {
        System.out.println("[WebSocket Client start]");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        } catch (Exception ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    /**
     * Stop the client when it is running.
     */
    private void stopClient(){
        System.out.println("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex){
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    // Process incoming json message
    private void processMessage(String jsonMessage) {

        // Parse incoming message
        DrawSocketMessage wsMessage;
        try {
            wsMessage = gson.fromJson(jsonMessage, DrawSocketMessage.class);
        }
        catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket Client ERROR: cannot parse Json message " + jsonMessage);
            return;
        }

        // Only operation update property will be further processed
        DrawSocketMessageOperation operation;
        operation = wsMessage.getOperation();
        if (operation == null || operation != DrawSocketMessageOperation.UPDATEPROPERTY) {
            System.out.println("[WebSocket Client ERROR: update property operation expected]");
            return;
        }

        // Obtain property from message
        String property = wsMessage.getProperty();
        if (property == null || "".equals(property)) {
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
        DrawMessage commMessage = new DrawMessage();
        commMessage.setProperty(property);
        commMessage.setContent(content);
        commMessage.setIdentifier(SocketMessageIdentifier.DRAWMESSAGE);

        // Notify observers
        this.setChanged();
        this.notifyObservers(commMessage);
    }
}
