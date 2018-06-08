package rpsclient.messageHandlers;

import rpsclient.IGameClient;
import rpsshared.MessageHandler;
import rpsshared.messages.RoundDrawMessage;

public class RoundDrawMessageHandler extends MessageHandler<RoundDrawMessage> {

    public RoundDrawMessageHandler(IGameClient client)
    {
        super(client);
    }

    @Override
    public void handleMessageInternal(RoundDrawMessage message, String sessionId) {
        getGameClient().processRoundDraw();
    }

}
