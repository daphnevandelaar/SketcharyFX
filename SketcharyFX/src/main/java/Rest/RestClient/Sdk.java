package Rest.RestClient;

import Rest.RestClient.clientResource.client.*;

/**
 * Entry-point for API calls.
 */
public class Sdk {

    private final Config config = new Config();

    /**
     * Returns the SDK configuration.
     */
    public Config getConfig() {
        return config;
    }

    public UserGetallClientResource userGetall() {
        return new UserGetallClientResource(config);
    }

    public UserGetbyusernameUsernameClientResource userGetbyusernameUsername(String username) {
        return new UserGetbyusernameUsernameClientResource(config, username);
    }

    public UserGetbyidIdClientResource userGetbyidId(String id) {
        return new UserGetbyidIdClientResource(config, id);
    }

    public UserClientResource user() {
        return new UserClientResource(config);
    }

    public SketcharyGetsketcharyIdClientResource sketcharyGetsketcharyId(String id) {
        return new SketcharyGetsketcharyIdClientResource(config, id);
    }

    public SketcharyGetrandomsketchyClientResource sketcharyGetrandomsketchy() {
        return new SketcharyGetrandomsketchyClientResource(config);
    }
}
