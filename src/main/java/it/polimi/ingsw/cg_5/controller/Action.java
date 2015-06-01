package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.*;


public abstract class Action {
protected final GameState gameState;

public Action(GameState gameState){
	this.gameState=gameState;
}
		
public GameState getGameState() {
	return gameState;
}

/**Abstract method that verifies all the conditions necessary to execute the specific action
 * 
 */
public abstract void execute();

}
