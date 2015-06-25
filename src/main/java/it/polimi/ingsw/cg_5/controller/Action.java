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

/**Abstract method that verifies all the conditions necessary to execute the specific action and goes to change the current state of the game.
 * 
 */
public abstract void execute();
/**Boolean method to control if the specific action can be done or not.
 * @return true if the command can be executed.
 */
public abstract boolean checkAction();

}


