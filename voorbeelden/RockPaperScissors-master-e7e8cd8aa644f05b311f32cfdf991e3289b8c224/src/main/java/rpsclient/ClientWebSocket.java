package rpsclient;

import rpsshared.Logging.LogLevel;
import rpsshared.Logging.Logger;
import rpsshared.WebSocketBase;
import rpsshared.messages.EncapsulatingMessage;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ClientWebSocket extends WebSocketBase implements IClientWebSocket {

    private static final String serverUri = "ws://localhost:8095/rockpaperscissors/";

    private Session session;

    private static ClientWebSocket instance = null;

    public static ClientWebSocket getInstance() {
        if (instance == null) {
            instance = new ClientWebSocket();
        }
        return instance;
    }

    @Override
    public void start() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(serverUri));

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }

    @Override
    public void stop() {
        try {
            if(session != null)
                session.close();

        } catch (Exception ex){
            Logger.getInstance().log(ex);
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
      onWebSocketMessageReceived(message, session.getId());
    }

    public void onWebSocketMessageReceived(String message, String sessionId)
    {
        EncapsulatingMessage msg = getGson().fromJson(message, EncapsulatingMessage.class);
        handler.processMessage(sessionId, msg.getMessageType(), msg.getMessageData());
    }

    IClientMessageProcessor handler;

    @Override
    public void setMessageHandler(IClientMessageProcessor handler) {
        this.handler = handler;
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        session = null;
    }

    private void sendMessageToServer(String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getInstance().log(ex);
        }
    }

    public void send(Object object)
    {
        String msg = getEncapsulatingMessageGenerator().generateMessageString(object);
        sendMessageToServer(msg);
    }
}
