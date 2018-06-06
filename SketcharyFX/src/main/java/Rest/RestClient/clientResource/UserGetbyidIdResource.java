package Rest.RestClient.clientResource;

public interface UserGetbyidIdResource {

    /**
     * changes the user with given ID.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Put
    void updateUserWithId();

}