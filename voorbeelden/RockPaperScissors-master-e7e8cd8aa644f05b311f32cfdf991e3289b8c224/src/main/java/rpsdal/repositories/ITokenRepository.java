package rpsdal.repositories;

import model.Token;
import rpsshared.interfaces.IRepository;

public interface ITokenRepository  extends IRepository<Token> {
    Token getTokenForUser(long userId);
    String generateToken(long userId);
}
