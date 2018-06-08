package rpsdal.repositories;

import model.Token;
import rpsdal.helpers.DateHelper;
import rpsshared.interfaces.IDataContext;

import java.util.Date;
import java.util.List;

public class TokenRepository extends RepositoryBase<Token> implements ITokenRepository {

    public TokenRepository(IDataContext context)
    {
        super(context);
    }

    public Token getTokenForUser(long userId)
    {
        Date currentDate = new Date();
        List<Token> allTokens = getDataContext().getAll(Token.class);

        for(Token t : allTokens)
        {
            if( t.getPlayerId() == userId) {
                Date newDateWithTTL = DateHelper.addMinutesToDate(t.getTTL(), t.getCreationDate());
                if (currentDate.before(newDateWithTTL)) {
                    // current date is before token expirationdate
                    return t;
                }
            }
        }
        return null;
    }

    public String generateToken(long userId)
    {
        String token = java.util.UUID.randomUUID().toString();
        Date date = new Date();
        Token t = new Token(token, date, 60, userId);
        getDataContext().add(t, Token.class);
        return token;
    }
}
