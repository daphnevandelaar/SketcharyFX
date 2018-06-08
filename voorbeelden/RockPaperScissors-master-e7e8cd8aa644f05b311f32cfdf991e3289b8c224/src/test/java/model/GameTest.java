package model;

import model.enums.GameState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpsserver.IServerMessageGenerator;
import stubs.ServerMessageGeneratorStub;

public class GameTest {

    private IPlayer p1;
    private IPlayer p2;

    IServerMessageGenerator messageGenerator;
    IGame game;

    @Before
    public void prepareTest(){
        p1 = new Player("1","p1");
        p2 = new Player("2","p2");

        messageGenerator = new ServerMessageGeneratorStub();
        game = new Game(messageGenerator);
    }

    @Test
    public void registerPlayerTest(){
        game.registerNewPlayer(p1.getName(), p1.getSessionId());
        Assert.assertEquals(1, game.getNumberOfPlayers());
        Assert.assertEquals(GameState.WAITINGFORPLAYERS, game.getGameState());
    }

    @Test
    public void registerSamePlayerNameTest(){
        game.registerNewPlayer(p1.getName(), p1.getSessionId());
        game.registerNewPlayer(p1.getName(), p2.getSessionId());
        Assert.assertEquals(1, game.getNumberOfPlayers());
        Assert.assertEquals(GameState.WAITINGFORPLAYERS, game.getGameState());
    }
}
