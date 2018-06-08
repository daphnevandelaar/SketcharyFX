package IntegrationTests;

import org.junit.Assert;
import org.junit.Test;
import rpsclient.*;
import rpsclient.messageHandlers.ClientMessageHandlerFactory;
import rpsshared.EncapsulatingMessageGenerator;
import rpsshared.interfaces.IClientGUI;
import rpsshared.interfaces.IMessageHandlerFactory;
import rpsshared.messages.RegistrationResultMessage;
import stubs.AppFlowStack;
import stubs.ClientGuiStub;

public class ClientMessagingIntegrationTest {

    @Test
    public void RegistrationResultTest()
    {
        RegistrationResultMessage resultMsg = new RegistrationResultMessage(true);
        String msg = new EncapsulatingMessageGenerator().generateMessageString(resultMsg);
        String sessionId = "1";

        IClientGUI clientGuiStub = new ClientGuiStub();
        IGameClient gameClient = new GameClient(null);
        gameClient.registerClientGUI(clientGuiStub);

        IMessageHandlerFactory factory = new ClientMessageHandlerFactory();
        IClientMessageProcessor processor = new ClientMessageProcessor(factory);
        processor.registerGameClient(gameClient);

        IClientWebSocket clientSocket = new ClientWebSocket();
        clientSocket.setMessageHandler(processor);
        clientSocket.onWebSocketMessageReceived(msg,sessionId);

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("processRegistrationResponse-true", AppFlowStack.getStack().get(0));
    }
}
