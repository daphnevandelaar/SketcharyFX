package DrawWebSocket.DrawSocketShared;

public class DrawSocketMessage {
    // Operation that is requested at client side
    private DrawSocketMessageOperation operation;

    // Property
    private String property;

    // Content
    private String content;

    public DrawSocketMessageOperation getOperation() {
        return operation;
    }

    public void setOperation(DrawSocketMessageOperation operation) {
        this.operation = operation;
    }

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
