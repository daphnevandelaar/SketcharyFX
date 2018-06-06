package Database.User;

import Database.IContext;
import Models.User;

public interface IUserContext<User> extends IContext<User>{
    Iterable<User> getUserByLevel(int level);
}
