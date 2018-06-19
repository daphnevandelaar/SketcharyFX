package Logic;

import Models.Game;
import Models.User;

public interface IGameLogic {
    Game startGame();
    Boolean sketchyGuessed(String sketchy, String message);
    User getSketcher();

}
