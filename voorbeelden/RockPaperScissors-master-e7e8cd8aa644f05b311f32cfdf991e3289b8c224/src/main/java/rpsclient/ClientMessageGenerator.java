package rpsclient;

import model.enums.Choice;
import rpsshared.messages.ClientChoiceMessage;
import rpsshared.messages.RegisterPlayerMessage;

public class ClientMessageGenerator implements IClientMessageGenerator {

    private IClientWebSocket clientSocket;

    public ClientMessageGenerator(IClientWebSocket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    public void registerPlayerOnServer(String name)
    {
        clientSocket.send(new RegisterPlayerMessage(name));
    }

    public void sendPlayerChoice(Choice c)
    {
        ClientChoiceMessage msg = new ClientChoiceMessage(c);
        clientSocket.send(msg);
    }
}
