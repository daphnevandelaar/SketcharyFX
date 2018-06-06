package Rest.RestClient.security;

public class AuthenticatorNotFoundException extends RuntimeException {

    public AuthenticatorNotFoundException(String message) {
        super(message);
    }
}
