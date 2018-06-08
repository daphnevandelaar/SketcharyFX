package Rest.RestClient.clientResource.client;


import Rest.RestClient.Config;
import Rest.RestClient.clientResource.UserGetrandomuserResource;
import Rest.RestClient.security.SecurityRuntimeConfigurator;
import Rest.shared.User;

public class UserGetrandomuserClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public UserGetrandomuserClientResource(Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/user/getrandomuser";
    }

    /**
     * search for random user.
     * 
     * @return {@link User}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public User getUserGetrandomuser() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(UserGetrandomuserResource.class).getUserGetrandomuser();
    }

}
