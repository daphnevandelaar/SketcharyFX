package ChatWebSocket.ChatSocketClient;

public interface IChatter {
    void start();                                                       //create connection
    void stop();                                                        //end connection
    void register(String userProperty, String eventProperty);           //user logs in to system
    void subscribe(String userProperty, String eventProperty);          //user subscribes to chat
    void unsubscribe(String userProperty, String eventProperty);        //user unsubscribes from chat
    void unregister(String userProperty, String eventProperty);         //user logs out from system
    void update(ChatMessage msg);                                       //user sends message to certain chat
    void userLogin(String userProperty, String eventProperty);
}
