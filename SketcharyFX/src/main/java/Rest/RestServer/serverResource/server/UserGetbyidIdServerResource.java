package Rest.RestServer.serverResource.server;

import Rest.RestServer.serverResource.UserGetbyidIdResource;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.util.logging.Level;

public class UserGetbyidIdServerResource extends AbstractServerResource implements UserGetbyidIdResource {

    // Define allowed roles for the method "put".
    private static final String[] put3AllowedGroups = new String[] {"anyone"};
    // Define denied roles for the method "put".
    private static final String[] put3DeniedGroups = new String[] {};

    public void store() throws Exception {
        checkGroups(put3AllowedGroups, put3DeniedGroups);
        

        try {
         } catch (Exception ex) {
            // In a real code, customize handling for each type of exception
            getLogger().log(Level.WARNING, "Error when executing the method", ex);
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                    ex.getMessage(), ex);
        }
    
        
    }


}

