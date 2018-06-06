package Database.User;

import Database.IContext;
import Database.Repository;
import Models.User;

public class UserRepository extends Repository<User> implements IUserRepository{

    private IUserContext userContext;

    public UserRepository(IUserContext<User> context) {
        super(context);
    }

    @Override
    public Iterable<User> getUserByLevel(int level) {
        return userContext.getUserByLevel(level);
    }
}
