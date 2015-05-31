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

public abstract void execute();

}
