package rpsshared;

import com.google.gson.Gson;
import model.IGame;
import rpsclient.IGameClient;
import rpsshared.interfaces.IMessageHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageHandler<T> implements IMessageHandler {

    private IGame game;
    private Gson gson;
    private IGameClient gameClient;

    public IGameClient getGameClient() {
        return gameClient;
    }

    public MessageHandler(IGameClient client)
    {
        this.gameClient = client;
        gson = new Gson();
    }

    public MessageHandler(IGame game)
    {
        this.game = game;
        gson = new Gson();
    }

    public void handleMessage(String data, String sessionId)
    {
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T msg = gson.fromJson(data, type);
        handleMessageInternal(msg, sessionId);
    }

    public abstract void handleMessageInternal(T message, String sessionId);

    public IGame getGame()
    {
        return game;
    }

}
