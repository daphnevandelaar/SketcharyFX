package Factory;

import Models.User;
import SketcharyGUI.Controller;

public class SketchyControllerFactory {
    public static Controller sketchyController(User user){
        return new Controller(user);
    }

}
