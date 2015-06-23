package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.GameState;

import java.util.TimerTask;

public class taskTimer extends TimerTask {
	private GameState gameState;
	
	public taskTimer(GameState gameState) {
		super();
		this.gameState = gameState;
	}

	@Override
	public void run() {
	
	//this.gameState.setChanged();
	//notifyObservers(this.matchIndex+" Timer expired for the player: "+ this.gameState.getCurrentCharacter().getPlayerID());
	this.gameState.goToNextCharacter();
		
	}

}
