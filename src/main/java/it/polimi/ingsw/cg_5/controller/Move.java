package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.*;

public class Move extends Action {
	Sector destinationSector;
	public Move(GameState gameState, Sector destinationSector) {
		super(gameState);
		this.destinationSector=destinationSector;
		
	}

	@Override
	public void execute() {
		if(gameState.getCurrentPlayer().getPlayerCharacter().isCanAttack() &&
			gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector().getReachableSectors
			(gameState.getCurrentPlayer().getPlayerCharacter().getMaxMove(),
			gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector()).contains(this.destinationSector) ){
			
		}

	}

}
