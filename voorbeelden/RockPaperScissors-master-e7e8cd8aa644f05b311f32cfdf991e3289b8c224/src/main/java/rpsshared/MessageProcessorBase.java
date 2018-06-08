package rpsshared;

import com.google.gson.Gson;
import model.IGame;
import rpsshared.interfaces.IMessageHandlerFactory;
import rpsshared.interfaces.IMessageProcessor;

public abstract class MessageProcessorBase implements IMessageProcessor {

    private IGame game;
    private IMessageHandlerFactory messageHandlerFactory;

    public IMessageHandlerFactory getMessageHandlerFactory() {
        return messageHandlerFactory;
    }

    public void registerGame(IGame game)
    {
        this.game = game;
    }

    public abstract void processMessage(String sessionId, String type, String data);

    public abstract void handleDisconnect(String sessionId);

    private Gson gson;

    public MessageProcessorBase(IMessageHandlerFactory messageHandlerFactory)
    {
        this.messageHandlerFactory = messageHandlerFactory;
        gson = new Gson();
    }



    public Gson getGson() {
        return gson;
    }

    public IGame getGame()
    {
        return game;
    }
}
