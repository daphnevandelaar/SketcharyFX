package rpsclient.messageHandlers;

import rpsclient.IGameClient;
import rpsshared.MessageHandler;
import rpsshared.messages.RegistrationResultMessage;

public class RegistrationResultMessageHandler extends MessageHandler<RegistrationResultMessage> {

    public RegistrationResultMessageHandler(IGameClient client)
    {
        super(client);
    }

    @Override
    public void handleMessageInternal(RegistrationResultMessage message, String sessionId) {
        getGameClient().handlePlayerRegistrationResponse(message.isResult());
    }

}
