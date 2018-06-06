package Rest.RestServer.serverResource;

import Rest.shared.User;
import org.restlet.resource.Get;

public interface UserGetbyusernameUsernameResource {

    @Get
    User represent() throws Exception;

}

