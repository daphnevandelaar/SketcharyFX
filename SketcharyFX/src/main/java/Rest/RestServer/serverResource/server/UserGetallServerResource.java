package Rest.RestServer.serverResource.server;

import Rest.shared.User;
import Rest.RestServer.serverResource.UserGetallResource;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserGetallServerResource extends AbstractServerResource implements UserGetallResource {

    // Define allowed roles for the method "get".
    private static final String[] get1AllowedGroups = new String[] {"anyone"};
    // Define denied roles for the method "get".
    private static final String[] get1DeniedGroups = new String[] {};

    public User represent() throws Exception {
       User result = null;
        checkGroups(get1AllowedGroups, get1DeniedGroups);
        

        try {
		
        // Query parameters

            User user = new User();
            user.setUsername("daf");
            user.setId("1");
            user.setLevel("4");
            user.setExperiencePoints("115");
            user.setPassword("geheim");

            User user2 = new User();
            user.setUsername("fad");
            user.setId("5");
            user.setLevel("1");
            user.setExperiencePoints("10");
            user.setPassword("geheim2");

            List<User> users = new ArrayList<>();
            users.add(user);
            users.add(user2);

	    result = user;
	    
	    // Initialize here your bean
         } catch (Exception ex) {
            // In a real code, customize handling for each type of exception
            getLogger().log(Level.WARNING, "Error when executing the method", ex);
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                    ex.getMessage(), ex);
        }
    
        return result;
    }


}

