package Rest.RestClient.clientResource;

import Rest.shared.User;

public interface UserGetrandomuserResource {

    /**
     * search for random user.
     *
     * @return  {@link User}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    User getUserGetrandomuser();

}