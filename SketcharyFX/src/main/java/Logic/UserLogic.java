package Logic;

import Database.User.IUserRepository;
import Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserLogic implements IUserLogic {

    IUserRepository userRepository;

    public UserLogic(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.getAll().forEach(users::add);

        return users;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public List<User> getUserByLevel(int level) {
        return null;
    }
}