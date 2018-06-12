package Factory;

import Logic.Authentication.Authentication;
import Logic.Authentication.Hashing.Plaintext;
import SketcharyGUI.LoginController;

public class LoginControllerFactory {
    public static LoginController loginController(){
        return new LoginController(new Authentication(UserFactory.ManageUsers(), new Plaintext()));
    }
}
