package Rest.RestClient.clientResource.client;


import Rest.RestClient.Config;
import Rest.RestClient.clientResource.UserGetbyidIdResource;
import Rest.RestClient.security.SecurityRuntimeConfigurator;

public class UserGetbyidIdClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private String id;

    private final String absolutePath;

    /**
     * Constructor.
     *
     * @param config
     *            Gathers configuration of the clientResource URI and security.
     * @param id
     *            ID of user to return
     */
    public UserGetbyidIdClientResource(Config config, String id) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.id = id;
        this.absolutePath = config.getBasePath() + "/user/getbyid/{id}";
    }

    /**
     * changes the user with given ID.
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void updateUserWithId() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("id", this.id);
        securityRuntimeConfigurator.configure(client);

        client.wrap(UserGetbyidIdResource.class).updateUserWithId();
    }

}
