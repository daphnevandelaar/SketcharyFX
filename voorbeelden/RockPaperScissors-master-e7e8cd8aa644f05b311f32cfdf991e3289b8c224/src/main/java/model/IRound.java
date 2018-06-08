package model;

import model.enums.Choice;

public interface IRound {

    IPlayer getLosingPlayer();

    boolean hasNoWinner();

    IPlayer getWinningPlayer();

    void processChoice(IPlayer p, Choice c);

    boolean isHasEnded();
}
