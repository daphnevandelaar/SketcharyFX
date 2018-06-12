package ChatSocket.ChatSocketServer;


import ChatSocket.ChatSocketShared.ChatSocketMessage;
import ChatSocket.ChatSocketShared.ChatSocketMessageOperation;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ServerEndpoint(value="/chat/")
public class ChatSocketServer {

    // All sessions
    private static final List<Session> sessions = new ArrayList();

    // Map each property to list of sessions that are subscribed to that property
    private static final Map<String,List<Session>> userPropertySessions = new HashMap();
    private static final Map<String,List<Session>> eventPropertySessions = new HashMap();


    @OnOpen
    public void onConnect(Session session){
        sessions.add(session);
        System.out.println("Connection established");

    }
    @OnMessage
    public void onText(String message, Session session){
        handleMessageFromClient(message, session);
    }
    @OnClose
    public void onClose(CloseReason reason, Session session){
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session){
        cause.printStackTrace(System.err);
    }

    private void handleMessageFromClient(String jsonMsg, Session session) {
        Gson gson = new Gson();
        ChatSocketMessage chatMsg = null;
        try {
            chatMsg = gson.fromJson(jsonMsg, ChatSocketMessage.class);
        }
        catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket ERROR: cannot parse Json message " + jsonMsg);
            return;
        }

        // Operation defined in message
        ChatSocketMessageOperation operation;
        operation = chatMsg.getOperation();

        // Process message based on operation
        String userProperty = chatMsg.getUserProperty();
        String eventProperty = chatMsg.getEventProperty();

        if(operation != null && userProperty != null){
            switch(operation){
                case REGISTER:
                    // Register property if not registered yet
                    if (eventPropertySessions.get(eventProperty) == null) {
                        eventPropertySessions.put(eventProperty, new ArrayList<Session>());
                        System.out.println("User logged in: " + userProperty + " on " + eventProperty);
                    }
                    break;
                case UNREGISTER:
                    if (userPropertySessions.get(userProperty) == null) {
                        userPropertySessions.get(userProperty).remove(session);
                    }
                    break;
                case SUBSCRIBEEVENT:
                    if (eventPropertySessions.get(eventProperty) != null) {
                            eventPropertySessions.get(eventProperty).add(session);
                            System.out.println("New subscription from: " + userProperty + " to: " + eventProperty);
                    }
                    break;
                case UNSUBSCRIBEEVENT:
                    if (userPropertySessions.get(userProperty) != null) {
                        if (userPropertySessions.get(eventProperty) != null) {
                            userPropertySessions.get(userProperty).remove(session);
                            userPropertySessions.get(eventProperty).remove(session);
                        }
                    }
                    break;
                case UPDATE:
                    // Send the message to all clients that are subscribed to this property
                    if (eventPropertySessions.get(eventProperty) != null) {
                        for(Session sess : eventPropertySessions.get(eventProperty)){
                                System.out.println("Msg to: " + sess.getId());
                                sess.getAsyncRemote().sendText(jsonMsg);
                            }
                        }
            }
        }
    }
}

