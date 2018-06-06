package Rest.RestServer.serverResource.server;

import Rest.shared.User;
import Rest.RestServer.serverResource.UserResource;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.util.logging.Level;

public class UserServerResource extends AbstractServerResource implements UserResource {

    // Define allowed roles for the method "post".
    private static final String[] post4AllowedGroups = new String[] {"anyone"};
    // Define denied roles for the method "post".
    private static final String[] post4DeniedGroups = new String[] {};

    public void add(User bean) throws Exception {
        checkGroups(post4AllowedGroups, post4DeniedGroups);
        
    	if (bean==null) {
    		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
    	}

        try {
         } catch (Exception ex) {
            // In a real code, customize handling for each type of exception
            getLogger().log(Level.WARNING, "Error when executing the method", ex);
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                    ex.getMessage(), ex);
        }
    
        
    }


}

