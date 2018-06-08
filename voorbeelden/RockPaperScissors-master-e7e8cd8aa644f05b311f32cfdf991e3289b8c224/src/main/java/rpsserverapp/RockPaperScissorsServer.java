package rpsserverapp;

import model.Game;
import model.IGame;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import rpsserver.IServerMessageGenerator;
import rpsserver.ServerMessageGenerator;
import rpsserver.ServerMessageProcessor;
import rpsserver.ServerWebSocket;
import rpsserver.messageHandlers.ServerMessageHandlerFactory;
import rpsshared.Logging.Logger;
import rpsshared.interfaces.IMessageHandlerFactory;
import rpsshared.interfaces.IMessageProcessor;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class RockPaperScissorsServer {

    private static final int PORT = 8095;


    public static void main(String[] args) {

        IMessageHandlerFactory factory = new ServerMessageHandlerFactory();
        IMessageProcessor messageHandler = new ServerMessageProcessor(factory);
        final ServerWebSocket socket = new ServerWebSocket();
        socket.setMessageHandler(messageHandler);

        IServerMessageGenerator messageGenerator = new ServerMessageGenerator(socket);

        IGame game = new Game(messageGenerator);
        messageHandler.registerGame(game);

        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);

        try {
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);

            // Add WebSocket endpoint to javax.websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(socket.getClass(), socket.getClass().getAnnotation(ServerEndpoint.class).value())
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> endpointClass) {
                            return (T) socket;
                        }
                    })
                    .build();
            wscontainer.addEndpoint(config);
            webSocketServer.start();
            webSocketServer.join();

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }
}

