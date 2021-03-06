package Database.User;

import Database.IContext;
import Models.User;

public interface IUserContext extends IContext<User> {
    Iterable<User> getUserByLevel(int level);
    User getRandomUser();
    User getPassword(String username);
}
