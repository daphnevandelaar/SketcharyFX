package Rest.RestClient.clientResource.client;


import Rest.RestClient.Config;
import Rest.RestClient.clientResource.UserResource;
import Rest.RestClient.security.SecurityRuntimeConfigurator;
import Rest.shared.User;

public class UserClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the clientResource URI and security.
     */
    public UserClientResource(Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/user";
    }

    /**
     * Adds a user into the system.
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void addUser(User bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        securityRuntimeConfigurator.configure(client);

        client.wrap(UserResource.class).addUser(bean);
    }

}
