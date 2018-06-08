package model;

import model.enums.Choice;
import model.enums.GameState;
import rpsserver.IServerMessageGenerator;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/*
THIS CLASS IS ONLY RESPONSIBLE FOR HANDLING GAME LOGIC / RULES
THE METHODS ARE CALLED FROM THE MESSAGE HANDLERS
WHEN A MESSAGE NEEDS TO BE SENT TO CLIENTS, THE MESSAGEGENERATOR CLASS IS USED
 */
public class Game  implements IGame, Observer {

    private ArrayList<IPlayer> players = new ArrayList<>();
    private IServerMessageGenerator messageGenerator;

    public GameState getGameState() {
        return gameState;
    }

    private GameState gameState = GameState.WAITINGFORPLAYERS;

    private IRound currentRound = null;
    private ArrayList<IRound> passedRounds = new ArrayList<>();

    public Game(IServerMessageGenerator messageGenerator)
    {
        this.messageGenerator = messageGenerator;
    }

    public int getNumberOfPlayers()
    {
        return players.size();
    }

    public void registerNewPlayer(String userName, String sessionId)
    {
        if(players.size() < 2)
        {
            if(checkPlayerNameAlreadyExists(userName)){
                    messageGenerator.notifyRegisterResult(sessionId, false);
                    return;
                }

            Player p = new Player(sessionId, userName);
            players.add(p);
            messageGenerator.notifyRegisterResult(sessionId, true);
            messageGenerator.notifyPlayerAdded(sessionId, userName);
            checkStartingCondition();
        }
        else
        {
            messageGenerator.notifyRegisterResult(sessionId, false);
        }
    }

    private boolean checkPlayerNameAlreadyExists(String userName)
    {
        for(IPlayer pl : players)
            if(pl.getName().equals(userName))
            {
                return true;
            }


        return false;
    }

    public void processClientDisconnect(String sessionId)
    {
        for(IPlayer pl : players)
            if(pl.getSessionId().equals(sessionId)) {
                players.remove(pl);
            }

            if(gameState != GameState.WAITINGFORPLAYERS)
            {
                currentRound = null;
                gameState = GameState.WAITINGFORPLAYERS;
            }
    }

    private IPlayer getPlayerWithSession(String sessionId)
    {
        for(IPlayer p : players)
            if(p.getSessionId().equals(sessionId))
                return p;
        return null;
    }

    public void processClientChoice(String sessionId, Choice choice)
    {
        if(this.gameState == GameState.ROUNDACTIVE)
        {
            IPlayer player = getPlayerWithSession(sessionId);
            currentRound.processChoice(player, choice);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        //ROUND HAS ENDED
        o.deleteObserver(this);
        gameState = GameState.ROUNDRESULT;

        if(currentRound.hasNoWinner())
            messageGenerator.notifyRoundDraw();
        else
            messageGenerator.notifyRoundEnded(currentRound.getWinningPlayer().getName(), currentRound.getLosingPlayer().getName());

        startNewRound();
    }

    private void startNewRound()
    {
        Round round = new Round();
        round.addObserver(this);
        if(currentRound != null)
            passedRounds.add(currentRound);

        currentRound = round;

        gameState = GameState.ROUNDACTIVE;
        messageGenerator.notifyStartRound();
    }

    private void checkStartingCondition()
    {
        if(players.size() ==2)
        {
            //Start the game
            startNewRound();
        }
    }
}
