package Logic;

import Factory.SketcharyFactory;
import Factory.UserFactory;
import Models.Game;
import Models.User;
import Rest.RestClient.Config;
import Rest.RestClient.clientResource.client.UserGetrandomuserClientResource;
import Rest.shared.ObjectCaster;

public class GameLogic implements IGameLogic{

    private ISketcharyLogic sketcharyLogic = SketcharyFactory.manageSketchys();
    private IUserLogic userLogic = UserFactory.ManageUsers();
    private Game game;

    @Override
    public Game startGame() {
        Config conf = new Config();
        conf.setBasePath("http://localhost:9001/v1");

        UserGetrandomuserClientResource getRandomUserClient = new UserGetrandomuserClientResource(conf);
        User user = ObjectCaster.castRestUserToModelUser(getRandomUserClient.getUserGetrandomuser(), new User());

        return new Game(sketcharyLogic.getRandomSketchary(), user);
    }
}
