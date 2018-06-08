package model;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void createPlayerTest()
    {
        IPlayer player = new Player("1", "player1");
        Assert.assertEquals("1", player.getSessionId());
        Assert.assertEquals("player1", player.getName());
    }
}
