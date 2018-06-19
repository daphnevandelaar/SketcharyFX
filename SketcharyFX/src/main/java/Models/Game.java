package Models;

import java.util.List;
import java.util.Timer;

public class Game {
    private int duration = 60;
    private Sketchary sketchy;
    private User sketcher;
    private participantRole role;
    private Room room;

    public participantRole getRole() {
        return role;
    }

    public void setRole(participantRole role) {
        this.role = role;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Game(Sketchary sketchy, User sketcher) {
        this.sketchy = sketchy;
        this.sketcher = sketcher;
        this.duration = 60;
    }

    public Game() {
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
