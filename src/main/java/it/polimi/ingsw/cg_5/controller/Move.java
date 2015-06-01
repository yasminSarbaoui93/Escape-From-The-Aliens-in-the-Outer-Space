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
		
		gameState.getCurrentCharacter().getCurrentSector().getCharacterList().
		remove(gameState.getCurrentCharacter());
		
		gameState.getMap().takeSector(destinationSector.getSectorName()).getCharacterList()
			.add(gameState.getCurrentCharacter());
		gameState.getCurrentCharacter().setCurrentSector(destinationSector);
		gameState.getTurn().setTurnState(TurnState.HASMOVED);
		
		}
	
	 public boolean checkMove(){
	  if(	gameState.getCurrentCharacter().getCurrentSector().getReachableSectors
			(gameState.getCurrentCharacter().getMaxMove(),
			gameState.getCurrentCharacter().getCurrentSector()).contains(this.destinationSector)
		    && gameState.getTurn().getTurnState().equals(TurnState.STARTED))
			
			return true;
	  
			
			else return false;
			}
	
	

}
