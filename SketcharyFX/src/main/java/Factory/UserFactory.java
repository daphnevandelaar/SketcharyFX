package Factory;

import Database.User.UserRepository;
import Database.User.UserSqlContext;
import Logic.IUserLogic;
import Logic.UserLogic;

public class UserFactory {

    public static IUserLogic ManageUsers(){
        return new UserLogic(new UserRepository(new UserSqlContext()));
    }

}

