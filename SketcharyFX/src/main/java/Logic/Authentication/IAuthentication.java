package Logic.Authentication;

import Models.User;

public interface IAuthentication {
    User signIn(String username, String password) throws invalidPasswordException;
    User signUp(String username, String password) throws Exception;
    User proceedAsGuest();
    boolean changePassword(String userName, String password, String newPassword) throws invalidPasswordException;
}
