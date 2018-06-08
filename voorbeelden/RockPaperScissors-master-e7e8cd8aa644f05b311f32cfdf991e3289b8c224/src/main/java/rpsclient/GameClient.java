package rpsclient;

import model.enums.Choice;
import rpsshared.interfaces.IClientGUI;

public class GameClient implements IGameClient {

    IClientMessageGenerator messageGenerator;

    private IClientGUI clientGUI;

    public void registerClientGUI(IClientGUI gui)
    {
        this.clientGUI = gui;
    }

    public GameClient(IClientMessageGenerator generator)
    {
        this.messageGenerator = generator;
    }

    public void registerPlayer(String userName)
    {
        messageGenerator.registerPlayerOnServer(userName);
    }

    public void handlePlayerRegistrationResponse(boolean success)
    {
        clientGUI.processRegistrationResponse(success);
    }

    public void processRoundStarted()
    {
        clientGUI.processRoundStarted();
    }

    public void handlePlayerRegistered(String playerName)
    {
        clientGUI.processPlayerRegistered(playerName);
    }

    public void sendPlayerChoice(Choice c)
    {
        messageGenerator.sendPlayerChoice(c);
    }

    public void processRoundDraw()
    {
        clientGUI.processRoundDraw();
    }

    public void processRoundResult(String winner, String loser)
    {
        clientGUI.processRoundResult(winner, loser);
    }
}
