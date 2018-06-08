package Database.User;

import Database.Repository;
import Models.User;

public class UserRepository extends Repository<User> implements IUserRepository{

    private IUserContext userContext;

    public UserRepository(IUserContext context) {
        super(context);
        userContext = context;
    }

    @Override
    public Iterable<User> getUserByLevel(int level) {
        return userContext.getUserByLevel(level);
    }

    @Override
    public User getRandomUser() {
        return userContext.getRandomUser();
    }
}
