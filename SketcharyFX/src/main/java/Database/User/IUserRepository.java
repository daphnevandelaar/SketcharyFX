package Database.User;

import Database.IRepository;
import Models.User;

public interface IUserRepository extends IRepository<User> {
    Iterable<User> getUserByLevel(int level);
    User getRandomUser();
}
