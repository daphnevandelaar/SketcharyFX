package rpsshared.messages;

public class PlayerHasRegisteredMessage {
    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public PlayerHasRegisteredMessage(String name)
    {
        this.playerName = name;
    }
}

