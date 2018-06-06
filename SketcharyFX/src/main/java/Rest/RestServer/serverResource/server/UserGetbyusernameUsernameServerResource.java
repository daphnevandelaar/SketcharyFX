package Rest.RestServer.serverResource.server;

import Rest.shared.User;
import Rest.RestServer.serverResource.UserGetbyusernameUsernameResource;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.util.logging.Level;

public class UserGetbyusernameUsernameServerResource extends AbstractServerResource implements UserGetbyusernameUsernameResource {

    // Define allowed roles for the method "get".
    private static final String[] get2AllowedGroups = new String[] {"anyone"};
    // Define denied roles for the method "get".
    private static final String[] get2DeniedGroups = new String[] {};

    public User represent() throws Exception {
       User result = null;
        checkGroups(get2AllowedGroups, get2DeniedGroups);
        

        try {
			
	    String usernamePathVariable = Reference.decode(getAttribute("username"));

        // Query parameters

            User user = new User();
            user.setUsername(usernamePathVariable);

            result = user;
	    //result = new net.apispark.webapi.representation.User();
	    
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

