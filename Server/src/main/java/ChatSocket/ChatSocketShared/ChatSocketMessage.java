package ChatSocket.ChatSocketShared;

import Sockets.SocketMessage;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChatSocketMessage extends SocketMessage {
    private ChatSocketMessageOperation operation;
    private String eventProperty;
    private String userProperty;
    private ArrayList<String> users;

    public ArrayList<String> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
    public String getEventProperty() {
        return eventProperty;
    }
    public void setEventProperty(String eventProperty) {
        this.eventProperty = eventProperty;
    }
    public String getUserProperty() {
        return userProperty;
    }
    public void setUserProperty(String userProperty) {
        this.userProperty = userProperty;
    }
    public ChatSocketMessageOperation getOperation() {
        return operation;
    }
    public void setOperation(ChatSocketMessageOperation operation){
        this.operation = operation;
    }
}
