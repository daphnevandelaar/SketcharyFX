package rpsclientapp.controllers;

import rpsclient.IGameClient;

public abstract class BaseController {

    public IGameClient getGameClient() {
        return gameClient;
    }

    private IGameClient gameClient;

    public BaseController(IGameClient gameClient)
    {
        this.gameClient = gameClient;
    }
}
