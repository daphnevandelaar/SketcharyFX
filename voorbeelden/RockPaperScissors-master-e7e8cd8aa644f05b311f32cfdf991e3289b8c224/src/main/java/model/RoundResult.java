package model;

public class RoundResult extends Entity {

    private String player1Name;
    private String player2Name;
    private String player1Choice;
    private String player2Choice;
    private String winningPlayerName;

    public RoundResult(String player1Name, String player2Name, String player1Choice, String player2Choice, String winningPlayerName) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Choice = player1Choice;
        this.player2Choice = player2Choice;
        this.winningPlayerName = winningPlayerName;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public String getPlayer1Choice() {
        return player1Choice;
    }

    public void setPlayer1Choice(String player1Choice) {
        this.player1Choice = player1Choice;
    }

    public String getPlayer2Choice() {
        return player2Choice;
    }

    public void setPlayer2Choice(String player2Choice) {
        this.player2Choice = player2Choice;
    }

    public String getWinningPlayerName() {
        return winningPlayerName;
    }

    public void setWinningPlayerName(String winningPlayerName) {
        this.winningPlayerName = winningPlayerName;
    }
}
