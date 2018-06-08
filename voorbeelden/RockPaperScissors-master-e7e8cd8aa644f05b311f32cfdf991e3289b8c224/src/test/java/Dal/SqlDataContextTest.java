package Dal;

import model.Player;
import model.RoundResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpsdal.SqlDataContext;

import java.util.List;

public class SqlDataContextTest {

    private  SqlDataContext context;

    @Before
    public void init()
    {
        context = new SqlDataContext();
    }

    @Test
    public void connectionTest()
    {
        List<Player> players = context.getAll(Player.class);
        Assert.assertEquals(0, players.size());
    }

    @Test
    public void addPlayerTest(){
        Player p = new Player("", "player1");
        p.setPassword("pwhash");
        context.add(p, Player.class);
    }

    @Test
    public void updatePlayerTest()
    {
        List<Player> players = context.getAll(Player.class);
        Player p = players.get(0);
        p.setPassword("pwhashupdated");
        context.update(p, Player.class);
    }

    @Test
    public void addRoundResultTest()
    {
        RoundResult rr = new RoundResult("p1", "p2", "ROCK", "PAPER","p2");
        context.add(rr, RoundResult.class);
    }

    @Test
    public void getRoundResult(){
        List<RoundResult> results = context.getAll(RoundResult.class);
        RoundResult r = results.get(0);
        Assert.assertNotNull(r);
    }
}
