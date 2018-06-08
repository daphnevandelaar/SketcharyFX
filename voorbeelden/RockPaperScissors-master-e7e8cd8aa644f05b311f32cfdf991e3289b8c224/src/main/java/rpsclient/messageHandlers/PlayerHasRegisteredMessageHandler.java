package rpsclient.messageHandlers;

import rpsclient.IGameClient;
import rpsshared.MessageHandler;
import rpsshared.messages.PlayerHasRegisteredMessage;

public class PlayerHasRegisteredMessageHandler extends MessageHandler<PlayerHasRegisteredMessage> {

    public PlayerHasRegisteredMessageHandler(IGameClient client)
    {
        super(client);
    }

    @Override
    public void handleMessageInternal(PlayerHasRegisteredMessage message, String sessionId) {
        getGameClient().handlePlayerRegistered(message.getPlayerName());
    }
}
