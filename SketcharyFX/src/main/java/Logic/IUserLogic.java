package Logic;

import Models.User;

import java.util.List;

public interface IUserLogic {

    List<User> getAllUsers();
    void insert(User user);
    List<User> getUserByLevel(int level);
    User getRandomUser();
}
