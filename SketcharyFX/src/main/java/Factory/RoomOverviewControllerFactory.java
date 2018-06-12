package Factory;

import Models.Room;
import Models.User;
import SketcharyGUI.SketcharyRoomOverviewController;

public class RoomOverviewControllerFactory {
    public static SketcharyRoomOverviewController sketchyController(User user){
        return new SketcharyRoomOverviewController(user);
    }
}
