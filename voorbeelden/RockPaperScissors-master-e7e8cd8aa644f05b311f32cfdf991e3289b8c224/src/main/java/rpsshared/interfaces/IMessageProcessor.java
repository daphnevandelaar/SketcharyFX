package rpsshared.interfaces;

import model.IGame;

public interface IMessageProcessor {
    void processMessage(String sessionId, String type, String data);

    void handleDisconnect(String sessionId);

    void registerGame(IGame game);
}
