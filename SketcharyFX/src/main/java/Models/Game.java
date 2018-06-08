package Models;

import java.util.List;
import java.util.Timer;

public class Game {
    private int duration;
    private Sketchary sketchy;
    private User sketcher;
    private List<User> guessers;


    public Game(Sketchary sketchy, User sketcher) {
        this.sketchy = sketchy;
        this.sketcher = sketcher;
        this.duration = 60;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Sketchary getSketchy() {
        return sketchy;
    }

    public void setSketchy(Sketchary sketchy) {
        this.sketchy = sketchy;
    }

    public User getSketcher() {
        return sketcher;
    }

    public void setSketcher(User sketcher) {
        this.sketcher = sketcher;
    }
}
