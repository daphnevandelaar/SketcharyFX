package rpsshared.interfaces;

public interface IClientGUI {
    void processRegistrationResponse(boolean resp);

    void processRoundStarted();

    void processPlayerRegistered(String playerName);

    void processRoundDraw();

    void processRoundResult(String winner, String loser);
}
