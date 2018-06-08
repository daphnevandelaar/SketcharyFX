package rpsdal;

import model.Entity;
import rpsdal.mapper.BaseDataMapper;
import rpsdal.mapper.DataMapperFactory;
import rpsshared.Logging.Logger;
import rpsshared.interfaces.IDataContext;
import java.sql.*;
import java.util.List;

public class SqlDataContext implements IDataContext {

    public Connection getConnection()
    {
        // Load the SQLServerDriver class, build the
        // connection string, and get a connection
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;" +
                    "database=RockPaperScissors;" +
                    "user=rockpaperscissors;" +
                    "password=rockpaperscissors";
            return DriverManager.getConnection(connectionUrl);
        }
        catch(Exception e)
        {
            Logger.getInstance().log(e);
            return null;
        }
    }

    public boolean executeNonQuery(String query)
    {
        try
        {
            Connection con = getConnection();
            // Create and execute an SQL statement that returns some data.
            try (Statement stmt = con.createStatement()) {
                return stmt.execute(query);
            }
        }
        catch(Exception e)
        {
            Logger.getInstance().log(e);
            return false;
        }
    }

    private <T> List<T> executeQuery(String query, Class<T> returnType)
    {
        try
        {
            Connection con = getConnection();
                // Create and execute an SQL statement that returns some data.
            try (Statement stmt = con.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    BaseDataMapper<T> mapper = null;
                    mapper = DataMapperFactory.getMapper(returnType);
                    if(!mapper.equals(null))
                        return mapper.mapFromDatabase(rs);

                    return null;
                }
            }
        }
        catch(Exception e)
        {
            Logger.getInstance().log(e);
            return null;
        }
    }

    public <T> T getSingle(long id, Class<T> returnType)
    {
        List<T> list = executeQuery("select top 1 * from ["+ returnType.getSimpleName() +"s] where Id=" + id, returnType);
        return list.get(0);
    }

    public <T> List<T> getAll(Class<T> returnType)
    {
        List<T> list = executeQuery("select * from ["+ returnType.getSimpleName() +"s]" , returnType);
        return list;
    }

    private <T> void writeToDatabase(Object obj, Class<T> returnType)
    {
        BaseDataMapper<T> mapper = null;
        mapper = DataMapperFactory.getMapper(returnType);
        if(!mapper.equals(null)) {
            String sql = mapper.mapToSql(obj);
            executeNonQuery(sql);
        }
    }

    public <T> void add(Entity obj, Class<T> returnType)
    {
        writeToDatabase(obj, returnType);
    }

    public <T> void update(Entity obj, Class<T> returnType)
    {
        writeToDatabase(obj, returnType);
    }

    public <T> void remove(Entity obj, Class<T> returnType)
    {
        String sql = "delete from ["+ returnType.getSimpleName() +"s] where Id=" + obj.getEntityId();
    }
}
