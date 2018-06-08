package rpsshared.interfaces;


public interface IMessageHandlerFactory {
    IMessageHandler getHandler(String simpleType, Object game);
}
