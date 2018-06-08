package model;

import java.util.Date;

public class Token extends Entity {

    private String tokenText;

    private Date creationDate;

    //Time to live -> how many seconds is this token valid?
    private int TTL;

    private long playerId;

    public Token(String tokenText, Date creationDate, int TTL, long playerId) {
        this.tokenText = tokenText;
        this.creationDate = creationDate;
        this.TTL = TTL;
        this.playerId = playerId;
    }

    public String getTokenText() {
        return tokenText;
    }

    public void setTokenText(String tokenText) {
        this.tokenText = tokenText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
}
