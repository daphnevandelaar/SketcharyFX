package stubs;

import rpsserver.IServerMessageGenerator;

public class ServerMessageGeneratorStub implements IServerMessageGenerator {

    @Override
    public void notifyPlayerAdded(String sessionId, String playerName) {

    }

    @Override
    public void notifyRegisterResult(String sessionId, boolean success) {

    }

    @Override
    public void notifyStartRound() {

    }

    @Override
    public void notifyRoundEnded(String winner, String loser) {

    }

    @Override
    public void notifyRoundDraw() {

    }
}
