package rpsclient.messageHandlers;

import rpsclient.IGameClient;
import rpsshared.interfaces.IMessageHandler;
import rpsshared.interfaces.IMessageHandlerFactory;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object game) {
        IGameClient gc = (IGameClient)game;

        switch(simpleType) {
            case "RegistrationResultMessage":
                return new RegistrationResultMessageHandler(gc);
            case "PlayerHasRegisteredMessage":
                return new PlayerHasRegisteredMessageHandler(gc);
            case "StartRoundMessage":
                return new StartRoundMessageHandler(gc);
            case "RoundDrawMessage":
                return new RoundDrawMessageHandler(gc);
            case "RoundResultMessage":
                return new RoundResultMessageHandler(gc);
            default:
                return null;
        }

    }
}
