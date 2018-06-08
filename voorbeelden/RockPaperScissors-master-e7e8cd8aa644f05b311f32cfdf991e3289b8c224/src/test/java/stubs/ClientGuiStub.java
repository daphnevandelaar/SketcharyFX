package stubs;

import rpsshared.interfaces.IClientGUI;

public class ClientGuiStub implements IClientGUI
{
    @Override
    public void processRegistrationResponse(boolean resp) {
            AppFlowStack.addStack("processRegistrationResponse-"+ resp);
    }

    @Override
    public void processRoundStarted() {

    }

    @Override
    public void processPlayerRegistered(String playerName) {

    }

    @Override
    public void processRoundDraw() {

    }

    @Override
    public void processRoundResult(String winner, String loser) {

    }
}
