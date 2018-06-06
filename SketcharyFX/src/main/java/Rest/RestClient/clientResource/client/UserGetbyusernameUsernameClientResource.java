package Rest.RestClient.clientResource.client;


import Rest.RestClient.Config;
import Rest.RestClient.clientResource.UserGetbyusernameUsernameResource;
import Rest.RestClient.security.SecurityRuntimeConfigurator;
import Rest.shared.User;

public class UserGetbyusernameUsernameClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private String username;

    private final String absolutePath;

    /**
     * Constructor.
     *
     * @param config
     *            Gathers configuration of the clientResource URI and security.
     * @param username
     *            name of user to return
     */
    public UserGetbyusernameUsernameClientResource(Config config, String username) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.username = username;
        this.absolutePath = config.getBasePath() + "/user/getbyusername/{username}";
    }

    /**
     * search for user with the ID.
     * 
     * @return {@link User}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public User searchUserByName() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("username", this.username);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(UserGetbyusernameUsernameResource.class).searchUserByName();
    }

}
