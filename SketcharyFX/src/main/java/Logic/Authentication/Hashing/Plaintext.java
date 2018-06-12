package Logic.Authentication.Hashing;

public class Plaintext implements IHashingMethod {
    @Override
    public String hashPassword(String password) {
        return password;
    }
}
