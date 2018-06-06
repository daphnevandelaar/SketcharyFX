package Logic;

import Database.User.IUserRepository;
import Models.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class UserLogic implements IUserLogic {

    IUserRepository userRepository;

    public UserLogic(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.getAll().forEach(users::add);

        return users;
    }

    @Override
    public void insert(User user) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> getUserByLevel(int level) {
        throw new NotImplementedException();
    }
}
