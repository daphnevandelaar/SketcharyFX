package Rest.shared;

public class ObjectCaster {

    public static User castModelUserToRestUser(User restUser, Models.User modelUser){

        restUser.setUsername(modelUser.getUsername());
        restUser.setExperiencePoints(Integer.toString(modelUser.getExpPoints()));
        restUser.setId(Integer.toString(modelUser.getId()));
        restUser.setLevel(Integer.toString(modelUser.getLevel()));
        restUser.setPassword(modelUser.getPassword());

        return restUser;
    }

    public static Models.User castRestUserToModelUser(User restUser, Models.User modelUser){
        modelUser.setUsername(restUser.getUsername());
        modelUser.setExpPoints(Integer.parseInt(restUser.getExperiencePoints()));
        modelUser.setId(Integer.parseInt(restUser.getId()));
        modelUser.setLevel(Integer.parseInt(restUser.getLevel()));
        modelUser.setPassword(restUser.getPassword());

        return modelUser;
    }
}
