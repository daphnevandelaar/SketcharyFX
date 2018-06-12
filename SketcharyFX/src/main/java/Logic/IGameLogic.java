package Logic;

import Models.Game;

public interface IGameLogic {
    Game startGame();
    Boolean sketchyGuessed(String sketchy, String message);
}
