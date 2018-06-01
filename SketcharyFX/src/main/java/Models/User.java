package Models;

public class User {
    private String username;
    private int level = 1;
    private int points = 0;

    public User(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return username + "   Level: " + level + "   Punten: " + points;
    }
}
