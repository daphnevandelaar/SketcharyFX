package rpsserver;

public interface IServerMessageGenerator {

        void notifyPlayerAdded(String sessionId, String playerName);

        void notifyRegisterResult(String sessionId, boolean success);

        void notifyStartRound();

        void notifyRoundEnded(String winner, String loser);

        void notifyRoundDraw();


}
