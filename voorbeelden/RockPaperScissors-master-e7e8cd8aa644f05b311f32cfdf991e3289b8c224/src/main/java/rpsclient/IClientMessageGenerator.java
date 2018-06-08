package rpsclient;

import model.enums.Choice;

public interface IClientMessageGenerator {
    void registerPlayerOnServer(String name);

    void sendPlayerChoice(Choice c);
}
