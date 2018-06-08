package rpsdal.mapper;

import model.Player;
import rpsshared.Logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDataMapper<T> extends BaseDataMapper<Player> {

    @Override
    public String mapToSqlInternal(Player object) {
        if(object.getEntityId() == 0)
        {
            //new
            return "insert into players (UserName, Password) values (' " + object.getName() + "' , '" + object.getPassword() +"')";
        }
        else
        {
            //update
            return "update players set UserName=' " + object.getName() + "', Password='" + object.getPassword() +"' where Id=" + object.getEntityId();
        }
    }

    @Override
    public List<Player> mapFromDatabaseInternal(ResultSet rs) {
        ArrayList<Player> players = new ArrayList<>();
        try {
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                Player p = new Player("", name);
                p.setEntityId(id);
                players.add(p);
            }
            return players;
        }
        catch(SQLException ex)
        {
            Logger.getInstance().log(ex);
            return players;
        }
    }
}
