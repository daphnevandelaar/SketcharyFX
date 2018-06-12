package Sockets;

public class SocketMessage {
    private String content;
    private SocketMessageIdentifier identifier;
//    private String userProperty;
//
//    public String getUserProperty() {
//        return userProperty;
//    }
//
//    public void setUserProperty(String userProperty) {
//        this.userProperty = userProperty;
//    }

    public SocketMessageIdentifier getIdentifier() {
        return identifier;
    }
    public void setIdentifier(SocketMessageIdentifier identifier) {
        this.identifier = identifier;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
