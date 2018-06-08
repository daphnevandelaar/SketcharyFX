package rpsserver;

import rpsshared.messages.*;

public class ServerMessageGenerator implements IServerMessageGenerator {

    private IServerWebSocket serverSocket;

    public ServerMessageGenerator(IServerWebSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void notifyPlayerAdded(String sessionId, String playerName) {
        PlayerHasRegisteredMessage msg = new PlayerHasRegisteredMessage(playerName);
        serverSocket.sendToOthers(sessionId, msg);
    }

    public void notifyRegisterResult(String sessionId, boolean success)
    {
        RegistrationResultMessage msg = new RegistrationResultMessage(success);
        serverSocket.sendTo(sessionId, msg);
    }

    public void notifyStartRound()
    {
        serverSocket.broadcast(new StartRoundMessage());
    }

    public void notifyRoundEnded(String winner, String loser)
    {
        serverSocket.broadcast(new RoundResultMessage(winner, loser));
    }

    public void notifyRoundDraw()
    {
        serverSocket.broadcast(new RoundDrawMessage());
    }

}
