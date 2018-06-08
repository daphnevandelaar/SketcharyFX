package Rest.RestServer.serverResource;

import Rest.shared.User;
import org.restlet.resource.Get;

public interface UserGetrandomuserResource {

    @Get
    User represent() throws Exception;

}

