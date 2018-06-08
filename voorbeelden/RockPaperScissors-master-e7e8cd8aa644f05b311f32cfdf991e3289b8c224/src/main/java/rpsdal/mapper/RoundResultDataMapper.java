package rpsdal.mapper;

import model.RoundResult;
import rpsshared.Logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundResultDataMapper<T> extends BaseDataMapper<RoundResult>  {

    @Override
    public List<RoundResult> mapFromDatabaseInternal(ResultSet rs) {
        ArrayList<RoundResult> results = new ArrayList<>();
        try {
            while (rs.next()) {
                long id = rs.getLong(1);

                String player1Name = rs.getString(2);
                String player2Name= rs.getString(3);
                String player1Choice= rs.getString(4);
                String player2Choice= rs.getString(5);
                String winningPlayerName= rs.getString(6);

                String name = rs.getString(2);

                RoundResult r = new RoundResult(player1Name, player2Name, player1Choice, player2Choice, winningPlayerName);
                r.setEntityId(id);
                results.add(r);
            }
            return results;
        }
        catch(SQLException ex)
        {
            Logger.getInstance().log(ex);
            return results;
        }
    }

    @Override
    public String mapToSqlInternal(RoundResult item) {
        if(item.getEntityId() == 0)
        {
            //new
            return "insert into roundresults ([Player1Name]\n" +
                    "           ,[Player2Name]\n" +
                    "           ,[Player1Choice]\n" +
                    "           ,[Player2Choice]\n" +
                    "           ,[WinningPlayerName])" +
                    "           values (' " + item.getPlayer1Name() + "' , '" + item.getPlayer2Name() +"' , '" + item.getPlayer1Choice() +"' , '" + item.getPlayer2Choice() +"' , '" + item.getWinningPlayerName() + "')";
        }
        else
        {
            //update
            return "update roundresults set Player1Name='" + item.getPlayer1Name() + "' , Player2Name='" +  item.getPlayer2Name() + "', Player1Choice='" + item.getPlayer1Choice() + "', Player2Choice='" + item.getPlayer2Choice() + "', WinningPlayerName='" + item.getWinningPlayerName() + "' where Id=" + item.getEntityId();
        }
    }

}
