package Rest.RestServer.serverResource.server;

import Factory.UserFactory;
import Logic.IUserLogic;
import Rest.RestServer.serverResource.UserGetrandomuserResource;
import Rest.shared.ObjectCaster;
import Rest.shared.User;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.util.logging.Level;

public class UserGetrandomuserServerResource extends AbstractServerResource implements UserGetrandomuserResource {

    // Define allowed roles for the method "get".
    private static final String[] get2AllowedGroups = new String[] {"anyone"};
    // Define denied roles for the method "get".
    private static final String[] get2DeniedGroups = new String[] {};

    public User represent() throws Exception {
       User result = null;
        checkGroups(get2AllowedGroups, get2DeniedGroups);
        

        try {
		
        IUserLogic userLogic = UserFactory.ManageUsers();
	    result = ObjectCaster.castModelUserToRestUser(new User(), userLogic.getRandomUser());
	    
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

