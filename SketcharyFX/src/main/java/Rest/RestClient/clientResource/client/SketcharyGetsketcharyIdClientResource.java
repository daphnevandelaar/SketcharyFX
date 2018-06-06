package Rest.RestClient.clientResource.client;


import Rest.RestClient.Config;
import Rest.RestClient.clientResource.SketcharyGetsketcharyIdResource;
import Rest.RestClient.security.SecurityRuntimeConfigurator;
import Rest.shared.Sketchary;

public class SketcharyGetsketcharyIdClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private String id;

    private final String absolutePath;

    /**
     * Constructor.
     *
     * @param config
     *            Gathers configuration of the clientResource URI and security.
     * @param id
     *            name of sketchary to return
     */
    public SketcharyGetsketcharyIdClientResource(Config config, String id) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.id = id;
        this.absolutePath = config.getBasePath() + "/sketchary/getsketchary/{id}";
    }

    /**
     * search for user with the ID.
     * 
     * @return {@link Sketchary}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public Sketchary getsasketchary() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("id", this.id);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(SketcharyGetsketcharyIdResource.class).getsasketchary();
    }

}
