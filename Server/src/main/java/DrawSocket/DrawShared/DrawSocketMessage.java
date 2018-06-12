package DrawSocket.DrawShared;

import Sockets.SocketMessage;

public class DrawSocketMessage extends SocketMessage {
    // Operation that is requested at client side
    private DrawSocketMessageOperation operation;

    // Property
    private String property;

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

}
