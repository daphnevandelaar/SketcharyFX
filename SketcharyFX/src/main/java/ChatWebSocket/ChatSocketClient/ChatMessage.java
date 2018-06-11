package ChatWebSocket.ChatSocketClient;

public class ChatMessage {

    private String content;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
