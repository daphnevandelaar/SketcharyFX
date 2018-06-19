package Logic;

import Factory.SketcharyFactory;
import Factory.UserFactory;
import Logic.SketchyChecker.CheckAlgorithm;
import Models.Game;
import Models.Room;
import Models.Sketchary;
import Models.User;
import Rest.RestClient.Config;
import Rest.RestClient.Sdk;
import Rest.RestClient.clientResource.client.SketcharyGetrandomsketchyClientResource;
import Rest.RestClient.clientResource.client.UserGetrandomuserClientResource;
import Rest.shared.ObjectCaster;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GameLogic implements IGameLogic{

    private ISketcharyLogic sketcharyLogic = SketcharyFactory.manageSketchys();
    private IUserLogic userLogic = UserFactory.ManageUsers();
    private Game game;

    Sdk sdk = new Sdk();


    Config conf = new Config();

    public GameLogic(Game game) {
        this.game = game;
        conf.setBasePath("http://localhost:9001/v1");
    }

    public GameLogic() {
        conf.setBasePath("http://localhost:9001/v1");
    }

    @Override
    public Game startGame() {
        game = new Game();

        Rest.shared.Sketchary sketchy = sdk.sketcharyGetrandomsketchy().getSketcharyGetrandomsketchy();
        Sketchary mSketchy = new Sketchary();
        mSketchy.setId(Integer.parseInt(sketchy.getId()));
        mSketchy.setSketchary(sketchy.getSketchary());
        game.setSketchy(mSketchy);

        game.setRoom(getRoom());



        return game;
    }

    //TODO: private maken na demo
    public Sketchary getSketchy(){
        SketcharyGetrandomsketchyClientResource getRandomSketchyClient = new SketcharyGetrandomsketchyClientResource(conf);
        return ObjectCaster.castRestSketchyToModelSketchy(getRandomSketchyClient.getSketcharyGetrandomsketchy(), new Sketchary());
    }

    public User getSketcher(){
        UserGetrandomuserClientResource getRandomUserClient = new UserGetrandomuserClientResource(conf);
        User user = ObjectCaster.castRestUserToModelUser(getRandomUserClient.getUserGetrandomuser(), new User());

        return user;
    }

    public Room getRoom(){
        throw new NotImplementedException();
    }

    @Override
    public Boolean sketchyGuessed(String sketchy, String message) {

        System.out.println("Sketchy: " + sketchy + " Msg: " + message);
        System.out.println(CheckAlgorithm.checkSketchy(sketchy, message));

       return CheckAlgorithm.checkSketchy(sketchy, message);
    }
}
