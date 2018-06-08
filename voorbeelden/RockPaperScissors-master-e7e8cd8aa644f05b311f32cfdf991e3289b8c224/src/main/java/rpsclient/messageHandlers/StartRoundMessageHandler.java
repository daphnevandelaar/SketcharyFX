package rpsclient.messageHandlers;

import rpsclient.IGameClient;
import rpsshared.MessageHandler;
import rpsshared.messages.StartRoundMessage;

public class StartRoundMessageHandler extends MessageHandler<StartRoundMessage> {

    @Override
    public void handleMessageInternal(StartRoundMessage message, String sessionId) {
        getGameClient().processRoundStarted();
    }

    public StartRoundMessageHandler(IGameClient client)
    {
        super(client);
    }
}
