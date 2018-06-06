package Models;

public class User {
    private int id;
    private String username;
    private String password;
    private int level = 1;
    private int expPoints = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //empty to create in database usercontext
    public User() {

    }


    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public User(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExpPoints(int expPoints) {
        this.expPoints = expPoints;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getExpPoints() {
        return expPoints;
    }

    @Override
    public String toString() {
        return username + "   Level: " + level + "   Punten: " + expPoints;
    }
}
