package stubs;

import rpsclient.IClientMessageProcessor;
import rpsclient.IClientWebSocket;

public class ClientWebSocketStub extends MessagingStub implements IClientWebSocket {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public void onWebSocketMessageReceived(String message, String sessionId)
    {

    }

    @Override
    public void send(Object object) {
        addMessage(object);
    }

    @Override
    public void setMessageHandler(IClientMessageProcessor handler) {

    }
}
