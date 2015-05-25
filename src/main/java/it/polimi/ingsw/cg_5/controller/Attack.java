package it.polimi.ingsw.cg_5.controller;
import it.polimi.ingsw.cg_5.model.*;

public class Attack extends Action {
	Sector attackedSector;
	
	public Attack (GameState gameState){
	super(gameState);
	}
	
	@Override
	public void execute() {
	if(gameState.getCurrentPlayer().getPlayerCharacter().isCanAttack()){
		
	}
	}

}
