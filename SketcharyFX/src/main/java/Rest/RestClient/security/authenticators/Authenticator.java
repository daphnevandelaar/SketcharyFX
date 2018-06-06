package Rest.RestClient.security.authenticators;

import org.restlet.resource.ClientResource;

/**
 * Configures a client clientResource so that it is able to connect to a remote clientResource that requires some kind of
 * authentication.
 */
public interface Authenticator {
    void configure(ClientResource clientResource);
}
