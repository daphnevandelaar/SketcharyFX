package Database.Sketchary;

import Database.SqlContext;
import Models.Sketchary;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SketcharySqlContext extends SqlContext<Sketchary> implements ISketcharyContext {

    private Sketchary getSketcharyFromResultSet(ResultSet rs){
        Sketchary sketchary = new Sketchary();

        try{
            sketchary.setId(Integer.parseInt(rs.getString("id")));
            sketchary.setSketchary(rs.getString("sketchary"));
        }
        catch(SQLException ex){System.out.println(ex);}

        return sketchary;
    }

    @Override
    public Sketchary getRandomSketchary() {

        String query = "SELECT public.\"funcGetRandomSketchary\"()";

        return getSketcharyFromResultSet(getDataByView(query));
    }

    @Override
    public Iterable<Sketchary> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public void insert(Sketchary entity) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Sketchary entity) {
        throw new NotImplementedException();
    }
}
