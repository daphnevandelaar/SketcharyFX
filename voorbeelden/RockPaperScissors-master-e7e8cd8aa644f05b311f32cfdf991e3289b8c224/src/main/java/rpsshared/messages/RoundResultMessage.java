package rpsshared.messages;

public class RoundResultMessage {

    private String winner;
    private String loser;

    public String getWinner() {
        return winner;
    }

    public String getLoser() {
        return loser;
    }

    public RoundResultMessage(String winner, String loser)
    {
        this.winner = winner;
        this.loser = loser;
    }


}
