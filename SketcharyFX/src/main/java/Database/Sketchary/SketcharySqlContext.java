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
            while (rs.next()){
                String record = rs.getString(1);

                sketchary.setSketchary(record.substring(1,record.indexOf(',')));
                sketchary.setId(Integer.parseInt(record.substring(record.indexOf(',')+1, record.length()-1)));

            }

        }
        catch(SQLException ex){System.out.println(ex);}

        return sketchary;
    }

    @Override
    public Sketchary getRandomSketchary() {
        String query = "SELECT public.\"funcGetRandomSketchary\"()";

        return getSketcharyFromResultSet(getDataByView(query));
    }

    public static void main(String[] args) {
        ISketcharyContext sketcharySqlContext = new SketcharySqlContext();
        Sketchary sk = sketcharySqlContext.getRandomSketchary();

        System.out.println(sk.toString());
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
