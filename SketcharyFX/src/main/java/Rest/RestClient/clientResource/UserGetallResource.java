package Rest.RestClient.clientResource;

import Rest.shared.User;

public interface UserGetallResource {

    /**
     * search for user with the ID.
     *
     * @return  {@link User}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    User getAllUsers();

}