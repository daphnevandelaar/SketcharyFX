package ChatWebSocket.ChatSocketClient;

import Sockets.SocketMessage;

public class ChatMessage extends SocketMessage {

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

}
