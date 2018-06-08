package rpsshared;

import com.google.gson.Gson;
import rpsshared.interfaces.IEncapsulatingMessageGenerator;
import rpsshared.interfaces.IMessageProcessor;
import rpsshared.interfaces.IWebSocket;

public abstract class WebSocketBase implements IWebSocket {

    private IMessageProcessor handler;

    public IEncapsulatingMessageGenerator getEncapsulatingMessageGenerator() {
        return encapsulatingMessageGenerator;
    }

    private IEncapsulatingMessageGenerator encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();

    private Gson gson;

    public WebSocketBase()
    {
        gson = new Gson();
    }

    public void setMessageHandler(IMessageProcessor handler)
    {
        this.handler = handler;
    }

    public abstract void start();

    public abstract void stop();

    public IMessageProcessor getHandler() {
        return handler;
    }

    public Gson getGson() {
        return gson;
    }
}
