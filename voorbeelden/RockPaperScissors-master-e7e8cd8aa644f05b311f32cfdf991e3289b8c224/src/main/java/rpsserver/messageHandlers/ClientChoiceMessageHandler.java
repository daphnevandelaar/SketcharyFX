package rpsserver.messageHandlers;

import model.IGame;
import rpsshared.MessageHandler;
import rpsshared.messages.ClientChoiceMessage;

public class ClientChoiceMessageHandler extends MessageHandler<ClientChoiceMessage> {

    public ClientChoiceMessageHandler(IGame game) { super(game);}

    @Override
    public void handleMessageInternal(ClientChoiceMessage message, String sessionId) {
        getGame().processClientChoice(sessionId, message.getChoice());
    }
}
