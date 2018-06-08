package rpsserver.messageHandlers;

import model.IGame;
import rpsshared.MessageHandler;
import rpsshared.messages.RegisterPlayerMessage;

public class RegisterPlayerMessageHandler extends MessageHandler<RegisterPlayerMessage> {

    public RegisterPlayerMessageHandler(IGame game) { super(game);}

    @Override
    public void handleMessageInternal(RegisterPlayerMessage message, String sessionId) {
        getGame().registerNewPlayer(message.getPlayerName(), sessionId);
    }
}
