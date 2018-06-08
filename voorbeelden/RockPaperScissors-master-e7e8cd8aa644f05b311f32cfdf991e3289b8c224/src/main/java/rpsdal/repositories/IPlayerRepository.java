package rpsdal.repositories;

import model.Player;
import rpsshared.interfaces.IRepository;

public interface IPlayerRepository extends IRepository<Player>{

    String login(ITokenRepository tokenRepos, String userName, String password);
}
