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
			System.out.println("\n\n il nuovo settore contiene"+gameState.getMap().takeSector(destinationSector.getSectorName()).getCharacterList());
			gameState.getCurrentCharacter().setCurrentSector(destinationSector);
			System.out.println("il nuovo current sector dell character è "+ gameState.getCurrentCharacter().getCurrentSector().getSectorName());
			System.out.println("\ne contiene i gioctori : \n"+gameState.getCurrentCharacter().getCurrentSector().getCharacterList());
			
		
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
