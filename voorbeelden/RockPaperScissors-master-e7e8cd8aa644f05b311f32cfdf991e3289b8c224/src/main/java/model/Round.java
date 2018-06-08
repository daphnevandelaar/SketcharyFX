package model;

import model.enums.Choice;
import java.util.HashMap;
import java.util.Observable;

/*
THIS CLASS PROCESSES ALL THE CLIENT ACTIONS FOR A GAME ROUND
ON ROUND END IT NOTIFIES IT'S OBSERVERS SO THEY CAN EXECUTE THE PROPER ACTIONS TO CONNECTED CLIENTS
 */

public class Round extends Observable implements IRound  {

    private HashMap<IPlayer, Choice> hmap = new HashMap<>();
    private IPlayer winningPlayer;
    private IPlayer losingPlayer;

    public boolean isHasEnded() {
        return hasEnded;
    }

    private boolean hasEnded;
    private boolean hasNoWinner;

    public Round(){
        this.hasEnded = false;
        this.hasNoWinner = true;
    }

    public IPlayer getWinningPlayer() {
        return winningPlayer;
    }

    public IPlayer getLosingPlayer() {
        return losingPlayer;
    }

    public boolean hasNoWinner() {
        return hasNoWinner;
    }

    public void setHasEnded(boolean value)
    {
        this.hasEnded = value;
        setChanged();
        notifyObservers();

    }

    public void processChoice(IPlayer p, Choice c)
    {
        if(this.hasEnded)
            return;

        if(!hmap.containsKey(p))
        {
            hmap.put(p, c);
            checkRoundResults();
        }
    }

    private void checkRoundResults(){
        if(hmap.size() == 2)
        {
            Choice choice1 = (Choice)hmap.values().toArray()[0];
            Choice choice2 = (Choice)hmap.values().toArray()[1];

            if(choice1.equals(choice2))
                hasNoWinner = true;
            else
            {
                this.hasNoWinner = false;
                if (choice1.equals(Choice.ROCK) &&
                        choice2.equals(Choice.SCISSORS)) {
                    setWinnerToFirstPlayer();
                }
                // Paper covers rock...
                else if (choice1.equals(Choice.PAPER) &&
                        choice2.equals(Choice.ROCK)) {
                    setWinnerToFirstPlayer();
                }
                // Scissors cut paper...
                else if (choice1.equals(Choice.SCISSORS) &&
                        choice2.equals(Choice.PAPER)) {
                    setWinnerToFirstPlayer();
                }
                else {
                    setWinnerToSecondPlayer();
                }
            }
            setHasEnded(true);
        }
    }

    private void setWinnerToFirstPlayer()
    {
        winningPlayer = (IPlayer)hmap.keySet().toArray()[0];
        losingPlayer = (IPlayer)hmap.keySet().toArray()[1];
    }

    private void setWinnerToSecondPlayer()
    {
        winningPlayer = (IPlayer)hmap.keySet().toArray()[1];
        losingPlayer = (IPlayer)hmap.keySet().toArray()[0];
    }
}
