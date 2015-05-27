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
			gameState.getCurrentPlayer().getPlayerCharacter().setCurrentSector(destinationSector);
		}
	
	 public boolean checkMove(){
	  if(	gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector().getReachableSectors
			(gameState.getCurrentPlayer().getPlayerCharacter().getMaxMove(),
			gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector()).contains(this.destinationSector)
			//||gamestate.getTurn.getTurnState.equals(turnState.Started))
			)
			return true;
	  
			
			else return false;
			}
	
	

}
