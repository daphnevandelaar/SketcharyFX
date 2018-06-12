package Factory;

import Models.Room;
import Models.User;
import SketcharyGUI.Controller;

public class SketchyControllerFactory {
    public static Controller sketchyController(User user, Room room){
        return new Controller(user, room);
    }

}
