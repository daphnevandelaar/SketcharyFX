package PlayersWebSocket.PlayerSocketClient;

import PlayersWebSocket.PlayerSocketShared.PlayerSocketOperation;

public class PlayerMessage {
    private PlayerSocketOperation operation;

    // Property
    private String property;

    // Content
    private String content;

    public PlayerSocketOperation getOperation() {
        return operation;
    }

    public void setOperation(PlayerSocketOperation operation) {
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
