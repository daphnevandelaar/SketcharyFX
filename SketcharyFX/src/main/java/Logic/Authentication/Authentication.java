package Logic.Authentication;

import Database.User.UserRepository;
import Factory.UserFactory;
import Logic.Authentication.Hashing.IHashingMethod;
import Logic.IUserLogic;
import Logic.UserLogic;
import Models.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Authentication implements IAuthentication{
    private IUserLogic userLogic;
    private IHashingMethod hashingMethod;

    public Authentication(IUserLogic userLogic, IHashingMethod hasher) {
        this.userLogic = userLogic;
        this.hashingMethod = hasher;
    }

    /**
     *
     * @param username
     * @param password
     */
    private User Validate(String username, String password) throws invalidPasswordException {
        //validate with the database if the password is correct.
        //then retrieve the user
        //todo if not correct return a fault message;
        //System.out.println("user is: " + username +" pass is: " + userLogic.getPassword(username).getPassword() + " input pas is: " + password);

        if(userLogic.getPassword(username).getPassword().equals(password)){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
        else{
            throw new invalidPasswordException("Wachtwoord onjuist");
        }
    }

    /**
     * @param username
     * @param password
     * @return UserObject which is the logged in user
     */
    @Override
    public User signIn(String username, String password) throws invalidPasswordException {
        String hash = hashingMethod.hashPassword(password);
        return Validate(username,hash);
    }

    /**
     *
     * @param username
     * @param password
     */
    @Override
    public User signUp(String username, String password) throws invalidPasswordException {
        isPasswordValid(password);
        String hash = hashingMethod.hashPassword(password);
        //return userRepository.createUser(username,hash);
        throw new NotImplementedException();
    }
    @Override
    public User proceedAsGuest() {
        //return userRepository.createGuest();
        throw new NotImplementedException();

    }

    /**
     *
     * @param password
     * @param newPassword
     */
    @Override
    public boolean changePassword(String userName, String password, String newPassword) throws invalidPasswordException {
//        isPasswordValid(newPassword);//this function can throw the invalid passwordException
//        String hash = hashingMethod.hashPassword(password);
//        if(userRepository.updateUser(userName,hash)==null)
//            return false;
//        return true;
        throw new NotImplementedException();
    }

    /**
     *
     * @param newPassword
     * @return boolean: true if the password is in line with all the given password validation methods
     */
    private void isPasswordValid(String newPassword) throws invalidPasswordException {
        if(newPassword.length()<3)
            throw new invalidPasswordException("Password is to short");

        //todo implement passwordRules
    }

}
