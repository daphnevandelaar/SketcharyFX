package model;

/*
THIS CLASS ONLY HAS PLAYER LOGIC LIKE THE PLAYER NAME AND SESSION ID
 */
public class Player extends Entity implements IPlayer {

    public String getName() {
        return playerName;
    }

    private String playerName;

    private String sessionId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public Player(String sessionId, String name)
    {
        this.playerName = name;
        this.sessionId = sessionId;
    }

    public String getSessionId()
    {
        return sessionId;
    }
}
