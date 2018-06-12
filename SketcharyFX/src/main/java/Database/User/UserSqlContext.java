package Database.User;

import Database.Sketchary.ISketcharyContext;
import Database.Sketchary.SketcharySqlContext;
import Database.SqlContext;
import Database.DbRecordReader;
import Models.Sketchary;
import Models.User;
import org.springframework.jdbc.core.SqlParameter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.Types.INTEGER;
import static java.sql.Types.VARCHAR;


public class UserSqlContext extends SqlContext<User> implements IUserContext{

    private Iterable<SqlParameter> UserSqlParameters(User user){

        ArrayList<SqlParameter> parameters = new ArrayList<SqlParameter>();

        //TODO: kijken of dit adden in een keer gaat..
        parameters.add(new SqlParameter(INTEGER, "UserId"));
        parameters.add(new SqlParameter(VARCHAR, "Username"));
        parameters.add(new SqlParameter(VARCHAR, "Password"));
        parameters.add(new SqlParameter(INTEGER, "UserLevel"));
        parameters.add(new SqlParameter(INTEGER, "UserExp"));

        for (SqlParameter parameter: parameters) {
            if(parameter.getName() == null){
                parameter = null;
            }
        }

        return parameters;
    }

    private Iterable<User> UsersFromResultSet(ResultSet rs){

        Iterable<User> users = new ArrayList<>();

        try{
            while (rs.next()){
                ((ArrayList<User>) users).add(UserFromNextResultSet(rs));
            }
        }
        catch (SQLException ex){ System.out.println(ex); }

        return users;
    }

    private User UserFromFunc(ResultSet rs){
        User user = new User();

        try{
            while (rs.next()){
                String record = rs.getString(1);
                user = DbRecordReader.defineUserFromDbRecord(record);
            }

        }
        catch(SQLException ex){System.out.println(ex);}

        return user;
    }

    private User PasswordFromFunc(ResultSet rs){
        User user = new User();

        try{
            while (rs.next()){
                String record = rs.getString(1);
                user.setPassword(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User UserFromNextResultSet(ResultSet rs){

        Models.User user =  new Models.User();

        try{
            user.setId(Integer.parseInt(rs.getString("UserId")));
            user.setUsername(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));
            user.setLevel(Integer.parseInt(rs.getString("UserLevel")));
            user.setExpPoints(Integer.parseInt(rs.getString("UserEXP")));
        }
        catch(SQLException ex){System.out.println(ex);}

        return (User)user;
    }

    public static void main(String[] args) {
        IUserContext userSqlContext = new UserSqlContext();
        User sk = userSqlContext.getPassword("daf");

        System.out.println(sk.getPassword());
    }

    @Override
    public Iterable<User> getAll() {

        Iterable<User> users = new ArrayList<User>();
        String query = "SELECT * FROM \"User\"";

        return UsersFromResultSet(getDataByView(query));
    }

    @Override
    public void insert(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public void update(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public Iterable getUserByLevel(int level){
        throw new NotImplementedException();
    }

    @Override
    public User getRandomUser() {
        String query = "SELECT public.\"funcGetRandomUser\"();";

        return UserFromFunc(getDataByView(query));
    }

    @Override
    public User getPassword(String username) {
        String query = "SELECT public.\"funcGetPassFromUsername\"('"+username+"');";

        return PasswordFromFunc(getDataByView(query));
    }
}
