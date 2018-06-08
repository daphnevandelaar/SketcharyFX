package model;

import model.enums.Choice;
import model.enums.GameState;

public interface IGame {
    void registerNewPlayer(String userName, String sessionId);

    void processClientChoice(String sessionId, Choice choice);

    void processClientDisconnect(String sessionId);

    int getNumberOfPlayers();

    GameState getGameState();
}
