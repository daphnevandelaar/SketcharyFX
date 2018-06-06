package Rest.RestClient.clientResource.client;


import Rest.RestClient.Config;
import Rest.RestClient.clientResource.UserGetallResource;
import Rest.RestClient.security.SecurityRuntimeConfigurator;
import Rest.shared.User;

public class UserGetallClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the clientResource URI and security.
     */
    public UserGetallClientResource(Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/user/getall";
    }

    /**
     * search for user with the ID.
     * 
     * @return {@link User}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public User getAllUsers() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(UserGetallResource.class).getAllUsers();
    }

}
