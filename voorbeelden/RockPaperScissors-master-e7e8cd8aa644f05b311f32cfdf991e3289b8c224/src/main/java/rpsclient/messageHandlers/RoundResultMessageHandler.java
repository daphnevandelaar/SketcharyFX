package rpsclient.messageHandlers;

import rpsclient.IGameClient;
import rpsshared.MessageHandler;
import rpsshared.messages.RoundResultMessage;

public class RoundResultMessageHandler extends MessageHandler<RoundResultMessage> {

    public RoundResultMessageHandler(IGameClient client)
    {
        super(client);
    }

    @Override
    public void handleMessageInternal(RoundResultMessage message, String sessionId) {
        getGameClient().processRoundResult(message.getWinner(), message.getLoser());
    }
}
