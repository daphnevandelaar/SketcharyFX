package DrawWebSocket.DrawSocketClient;

import Sockets.SocketMessage;

public class DrawMessage extends SocketMessage {
    // Property to which receiving clients should be subscribed
    private String property;

    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
}
