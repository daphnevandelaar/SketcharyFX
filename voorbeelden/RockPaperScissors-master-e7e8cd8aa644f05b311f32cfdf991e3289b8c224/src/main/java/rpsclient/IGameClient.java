package rpsclient;

import model.enums.Choice;
import rpsshared.interfaces.IClientGUI;

public interface IGameClient{
        void registerPlayer(String userName);

        void handlePlayerRegistrationResponse(boolean success);

        void registerClientGUI(IClientGUI gui);

        void processRoundStarted();

        void sendPlayerChoice(Choice c);

        void handlePlayerRegistered(String playerName);

        void processRoundDraw();

        void processRoundResult(String winner, String loser);
}
