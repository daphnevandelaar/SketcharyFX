package Factory;

import SketcharyGUI.LoginController;

public class LoginControllerFactory {
    public static LoginController loginController(){
        return new LoginController();
    }
}
