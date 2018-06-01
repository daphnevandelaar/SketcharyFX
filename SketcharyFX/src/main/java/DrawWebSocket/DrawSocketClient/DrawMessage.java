package DrawWebSocket.DrawSocketClient;

public class DrawMessage {
    // Property to which receiving clients should be subscribed
    private String property;

    // Content of the message
    private String content;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
