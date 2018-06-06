package Rest.RestServer.serverResource;

import Rest.shared.User;
import org.restlet.resource.Post;

public interface UserResource {

    @Post
    void add(User bean) throws Exception;

}

