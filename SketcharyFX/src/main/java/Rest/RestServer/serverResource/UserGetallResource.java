package Rest.RestServer.serverResource;

import Rest.shared.User;
import org.restlet.resource.Get;

public interface UserGetallResource {

    @Get
    User represent() throws Exception;

}

