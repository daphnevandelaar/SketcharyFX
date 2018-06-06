package Database.User;

import Database.SqlContext;
import Models.User;
import org.springframework.jdbc.core.SqlParameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.Types.INTEGER;
import static java.sql.Types.VARCHAR;


public class UserSqlContext<User> extends SqlContext<User> implements IUserContext{

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

    @Override
    public Iterable<User> getAll() {

        Iterable<User> users = new ArrayList<User>();
        String query = "SELECT * FROM \"User\"";

        return UsersFromResultSet(getDataByView(query));
    }
    
    @Override
    public void insert(User entity) {

    }

    @Override
    public void update(User entity) {



    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public Iterable getUserByLevel(int level) {
        return null;
    }
}
