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
	
	 /**It controls that the destination sector is contained in the list of reachable sectors of the current carachter (depending on the max move and on the position).
	  * If the turn is on the state "STARTED" and the selected sector is reachable, then the character can move there.
	 * @return
	 */
	public boolean checkAction(){
	  if(	gameState.getCurrentCharacter().getCurrentSector().getReachableSectors
			(gameState.getCurrentCharacter().getMaxMove(),
			gameState.getCurrentCharacter().getCurrentSector()).contains(this.destinationSector)
		    && gameState.getTurn().getTurnState().equals(TurnState.STARTED))
			
			return true;
	  
			
			else return false;
			}
	
	

}
