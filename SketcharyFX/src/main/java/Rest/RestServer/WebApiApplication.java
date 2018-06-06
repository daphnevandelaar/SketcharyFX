package Rest.RestServer;

import Rest.RestServer.serverResource.server.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MemoryRealm;
import org.restlet.security.Role;
import org.restlet.security.User;
import org.restlet.service.CorsService;

public class WebApiApplication extends Application {

    /*
     * Define route constants
     */
    public static final String ROUTE_USERGETALL = "/user/getall";

    public static final String ROUTE_USERGETBYUSERNAMEUSERNAME = "/user/getbyusername/{username}";

    public static final String ROUTE_USERGETBYIDID = "/user/getbyid/{id}";

    public static final String ROUTE_USER = "/user";

    public static final String ROUTE_SKETCHARYGETSKETCHARYID = "/sketchary/getsketchary/{id}";


    /*
     * Define role names
     */
    public static final String ROLE_ADMIN = "admin";

    public static final String ROLE_ANYONE = "anyone";

    public static final String ROLE_DEV = "cellroledev";

    public static final String ROLE_OWNER = "cellroleowner";

    public static final String ROLE_USER = "cellroleuser";

    private String versionFull;

    private int versionMajor;

    private int versionMicro;

    private int versionMinor;
    
    public WebApiApplication() {
    	setName("simpleInventoryApi");
        CorsService corsService = new CorsService();
        corsService.setAllowedCredentials(true);
        corsService.setSkippingResourceForCorsOptions(true);
        getServices().add(corsService);
    }

	private ChallengeAuthenticator createApiGuard(Restlet next) {

        ChallengeAuthenticator apiGuard = new ChallengeAuthenticator(
                getContext(), ChallengeScheme.HTTP_BASIC, "realm");

        // Create in-memory users and roles.
        MemoryRealm realm = new MemoryRealm();
        User owner = new User("owner", "owner");
        realm.getUsers().add(owner);
        realm.map(owner, Role.get(this, ROLE_OWNER));
        realm.map(owner, Role.get(this, ROLE_USER));
        realm.map(owner, Role.get(this, ROLE_DEV));
        User admin = new User("admin", "admin");
        realm.getUsers().add(admin);
        realm.map(admin, Role.get(this, ROLE_ADMIN));
        realm.map(admin, Role.get(this, ROLE_OWNER));
        realm.map(admin, Role.get(this, ROLE_USER));
        realm.map(admin, Role.get(this, ROLE_DEV));
        User user = new User("user", "user");
        realm.getUsers().add(user);
        realm.map(user, Role.get(this, ROLE_USER));

        // Verifier : to check authentication
        apiGuard.setVerifier(realm.getVerifier());
        // Enroler : add authorization roles
        apiGuard.setEnroler(realm.getEnroler());

        // You can also create your own authentication/authorization system by
        // creating classes extending SecretVerifier or LocalVerifier (for
        // authentication) and Enroler (for authorization) and set these to the
        // guard.

        apiGuard.setNext(next);

        // In case of anonymous access supported by the API.
        apiGuard.setOptional(true);

        return apiGuard;
    }
    
    public Router createApiRouter() {
        Router apiRouter = new Router(getContext());
        apiRouter.attach(ROUTE_USERGETALL, UserGetallServerResource.class);
        apiRouter.attach(ROUTE_USERGETBYUSERNAMEUSERNAME, UserGetbyusernameUsernameServerResource.class);
        apiRouter.attach(ROUTE_USERGETBYIDID, UserGetbyidIdServerResource.class);
        apiRouter.attach(ROUTE_USER, UserServerResource.class);
        apiRouter.attach(ROUTE_SKETCHARYGETSKETCHARYID, SketcharyGetsketcharyIdServerResource.class);

        return apiRouter;
	}

    public Restlet createInboundRoot() {

        // Router for the API's resources
        Router apiRouter = createApiRouter();
        // Protect the set of resources
        ChallengeAuthenticator guard = createApiGuard(apiRouter);

        return guard;
    }

}
