package Sockets;

import ChatSocket.ChatSocketServer.ChatSocketServer;
import DrawSocket.DrawSocketServer.DrawSocketServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;

public class DrawStartup {
    private static final int drawPORT = 8095;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startDrawSocketServer();
    }


    // Start the web socket server
    private static void startDrawSocketServer() {
        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(drawPORT);
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
            wscontainer.addEndpoint(DrawSocketServer.class);

            webSocketServer.start();
            //server.dump(System.err);

            webSocketServer.join();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

}
