package rpsshared.interfaces;

public interface IWebSocket {
    void start();

    void stop();

    void setMessageHandler(IMessageProcessor handler);
}
