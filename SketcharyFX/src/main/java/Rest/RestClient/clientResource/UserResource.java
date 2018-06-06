package Rest.RestClient.clientResource;

import Rest.shared.User;

public interface UserResource {

    /**
     * Adds a user into the system.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void addUser(User bean);

}