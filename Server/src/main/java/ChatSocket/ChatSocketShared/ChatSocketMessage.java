package ChatSocket.ChatSocketShared;

import Sockets.SocketMessage;

public class ChatSocketMessage extends SocketMessage {
    private ChatSocketMessageOperation operation;
    private String eventProperty;
    private String userProperty;

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
