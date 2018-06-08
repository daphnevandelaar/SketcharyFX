package model;

import model.enums.Choice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoundTest {

    private IRound round;
    private IPlayer p1;
    private IPlayer p2;

    @Before
    public void prepareTest()
    {
        round = new Round();
        p1 = new Player("1","p1");
        p2 = new Player("2","p2");
    }

    @Test
    public void processChoiceWinTest()
    {
        round.processChoice(p1, Choice.PAPER);
        Assert.assertEquals(round.hasNoWinner(), true);
        Assert.assertEquals(round.isHasEnded(), false);

        round.processChoice(p2, Choice.SCISSORS);
        Assert.assertEquals(round.hasNoWinner(), false);
        Assert.assertEquals(round.isHasEnded(), true);
        Assert.assertEquals(round.getWinningPlayer().getName(), p2.getName());
        Assert.assertEquals(round.getLosingPlayer().getName(), p1.getName());
    }

    @Test
    public void processChoiceDraw()
    {
        round.processChoice(p1, Choice.PAPER);
        round.processChoice(p2, Choice.PAPER);
        Assert.assertEquals(round.hasNoWinner(), true);
        Assert.assertEquals(round.isHasEnded(), true);
    }
}
