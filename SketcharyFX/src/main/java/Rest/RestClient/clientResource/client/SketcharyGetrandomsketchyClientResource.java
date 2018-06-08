package Rest.RestClient.clientResource.client;

import Rest.RestClient.Config;
import Rest.RestClient.clientResource.SketcharyGetrandomsketchyResource;
import Rest.RestClient.security.SecurityRuntimeConfigurator;
import Rest.shared.Sketchary;

public class SketcharyGetrandomsketchyClientResource {

    private final SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public SketcharyGetrandomsketchyClientResource(Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/sketchary/getrandomsketchy";
    }

    /**
     * gets random sketchy out db.
     * 
     * @return {@link Sketchary}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public Sketchary getSketcharyGetrandomsketchy() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(SketcharyGetrandomsketchyResource.class).getSketcharyGetrandomsketchy();
    }

}
