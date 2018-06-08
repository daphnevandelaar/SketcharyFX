package rpsshared.messages;

public class RegisterPlayerMessage {
    private String playerName;

    public RegisterPlayerMessage(String name)
    {
        this.playerName = name;
    }

    public String getPlayerName(){
        return playerName;
    }
}
