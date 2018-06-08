package rpsserver.messageHandlers;

import model.IGame;
import rpsshared.interfaces.IMessageHandler;
import rpsshared.interfaces.IMessageHandlerFactory;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object game)
    {
        IGame igame = (IGame)game;
        switch(simpleType)
        {
            case "RegisterPlayerMessage":
                return new RegisterPlayerMessageHandler(igame);
            case "ClientChoiceMessage":
                return new ClientChoiceMessageHandler(igame);
            default:
                return null;
        }
    }
}
