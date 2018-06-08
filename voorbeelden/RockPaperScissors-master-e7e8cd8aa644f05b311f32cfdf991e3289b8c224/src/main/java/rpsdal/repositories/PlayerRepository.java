package rpsdal.repositories;

import model.Player;
import model.Token;
import rpsdal.helpers.DateHelper;
import rpsshared.interfaces.IDataContext;
import rpsshared.interfaces.IRepository;

import java.util.Date;
import java.util.List;

public class PlayerRepository extends RepositoryBase<Player> implements IPlayerRepository {

    public PlayerRepository(IDataContext context)
    {
        super(context);
    }

    public String login(ITokenRepository tokenRepos, String userName, String password)
    {
        List<Player> players = getAll();
        for(Player p : players)
        {
            if(p.getName().equals(userName)
                    && p.getPassword().equals(password))
            {
                //Check existing token
                Token existingToken = tokenRepos.getTokenForUser(p.getEntityId());
                if(existingToken != null)
                    return existingToken.getTokenText();

                //OLD TOKEN NOT FOUND SO GENERATE A NEW ONE
                String token = tokenRepos.generateToken(p.getEntityId());

                return token;
            }
        }
        return "";
    }
}
