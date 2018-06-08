package rpsshared.messages;

import model.enums.Choice;

public class ClientChoiceMessage {

    private Choice choice;

    public Choice getChoice() {
        return choice;
    }

    public ClientChoiceMessage(Choice c)
    {
        this.choice = c;
    }
}
