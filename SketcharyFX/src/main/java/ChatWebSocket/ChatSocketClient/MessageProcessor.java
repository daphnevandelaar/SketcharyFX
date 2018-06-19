package ChatWebSocket.ChatSocketClient;

import ChatWebSocket.ChatSocketShared.ChatSocketMessage;
import ChatWebSocket.ChatSocketShared.ChatSocketMessageOperation;
import Sockets.SocketMessageIdentifier;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class MessageProcessor {

    private static Gson gson = new Gson();

    public static ChatMessage processor(String jsonMsg){
        ChatSocketMessage chatSocketMessage = parseJsonMessage(jsonMsg);
        ChatSocketMessageOperation operation = chatSocketMessage.getOperation();

        String userProperty = chatSocketMessage.getUserProperty();
        String eventProperty = chatSocketMessage.getEventProperty();
        String content = chatSocketMessage.getContent();
        ArrayList<String> users = chatSocketMessage.getUsers();

        ChatMessage commMessage = new ChatMessage();
        commMessage.setUserProperty(userProperty);
        commMessage.setEventProperty(eventProperty);
        commMessage.setContent(content);
        commMessage.setUsers(users);

        switch (operation){
            case USERLOGIN:
                commMessage.setIdentifier(SocketMessageIdentifier.PLAYERMESSAGE);
                break;
            case UPDATE:
                commMessage.setIdentifier(SocketMessageIdentifier.CHATMESSAGE);
        }
        return commMessage;
    }



    private static ChatSocketMessage parseJsonMessage(String jsonMessage){
        ChatSocketMessage chatSocketMessage = new ChatSocketMessage();
        try {
            chatSocketMessage = gson.fromJson(jsonMessage, ChatSocketMessage.class);
        }
        catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket Client ERROR: cannot parse Json message " + jsonMessage);
        }

        return chatSocketMessage;
    }
}
