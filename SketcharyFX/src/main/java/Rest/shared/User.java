package Rest.shared;

public class User {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private String level;


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    private String experiencePoints;


    public String getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(String experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

}
