package PlayersWebSocket.PlayerSocketServer;

import PlayersWebSocket.PlayerSocketShared.PlayerSocketMessage;
import PlayersWebSocket.PlayerSocketShared.PlayerSocketOperation;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value="/player/")
public class PlayerSocketServer {

    private static final List<Session> sessions = new ArrayList();
    private static final Map<String,List<Session>> propertySessions = new HashMap();

    @OnOpen
    public void onPlayerSocketConnection(Session session){
        System.out.println("[WebSocket Connected] SessionID: " + session.getId());
        String message = String.format("[New client with client side session ID]: %s", session.getId());
        sessions.add(session);
        System.out.println("[#sessions]: " + sessions.size());
    }

    @OnMessage
    public void onPlayerSocketText(String message, Session session){
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Received] : " + message);
        handleMessageFromClient(message, session);
    }

    private void handleMessageFromClient(String jsonMsg, Session session) {
        Gson gson = new Gson();
        PlayerSocketMessage playerSocketMessage = null;

        try{
            playerSocketMessage = gson.fromJson(jsonMsg, PlayerSocketMessage.class);
            System.out.println("HandleMessageFromClient "+playerSocketMessage);
        }catch (JsonSyntaxException jsnEx){
            System.out.println(jsnEx.getMessage());
            return;
        }

        PlayerSocketOperation operation = playerSocketMessage.getOperation();

        String property = playerSocketMessage.getProperty();
        if(operation != null && property != null && !"".equals(property)){
            switch (operation){
                case ONLINE:
                    if(propertySessions.get(property) != null){
                        propertySessions.put(property, new ArrayList<Session>());
                        propertySessions.get(property).add(session);
                    }
                case OFFLINE:
                    if (propertySessions.get(property) != null){
                        propertySessions.get(property).remove(session);
                    }
                case UPDATE:
                    if (propertySessions.get(property) != null) {
                        System.out.println("[PlayerSocket send ] " + jsonMsg + " to: ");
                        for (Session sess : propertySessions.get(property)) {
                            // Use asynchronous communication
                            System.out.println("\t\t >> Client associated with server side session ID: " + sess.getId());
                            sess.getAsyncRemote().sendText(jsonMsg);
                        }
                        System.out.println("[WebSocket end sending message to subscribers]");
                    }
                    break;
                default:
                    System.out.println("[WebSocket ERROR: cannot process Json message] " + jsonMsg);
                    break;
            }
        }
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason, Session session){
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Socket Closed]: " + reason);
        sessions.remove(session);
    }
}
