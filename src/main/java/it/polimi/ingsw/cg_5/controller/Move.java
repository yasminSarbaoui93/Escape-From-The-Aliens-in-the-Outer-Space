package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;

public class Move extends Action {
	EscapeHatchCard escapeCard=null;
	Sector destinationSector=null;
	public Sector getDestinationSector() {
		return destinationSector;
	}

	Character escapedCharacter;
	public Character getEscapedCharacter() {
		return escapedCharacter;
	}

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
		
		if(destinationSector.getClass()==EscapeSector.class){
			escapeCard=(EscapeHatchCard) gameState.getEscapeHatchDeck().removeCard();
			
			if(escapeCard.getEscapeHatchType()==EscapeHatchType.GREEN_SHALLOP){
			     escapedCharacter=gameState.getCurrentCharacter();
				 gameState.goToNextCharacter();
				 gameState.getCharacterList().remove(escapedCharacter);
				 gameState.getTurn().setTurnState(TurnState.STARTED);
				 
				
			}
			
			((EscapeSector) destinationSector).setAvailable(false);
		}
		
		}
	
	 /**It controls that the destination sector is contained in the list of reachable sectors of the current carachter (depending on the max move and on the position).
	  * If the turn is on the state "STARTED" and the selected sector is reachable, then the character can move there.
	 * @return
	 */
	public boolean checkAction(){
		if(destinationSector.getClass()==EscapeSector.class){
			if(gameState.getCurrentCharacter().getClass()==Alien.class)
				return false;
			if(!((EscapeSector) destinationSector).isAvailable()){
				return false;
				
			}
			
		}
		
	  if(	gameState.getCurrentCharacter().getCurrentSector().getReachableSectors
			(gameState.getCurrentCharacter().getMaxMove(),
			gameState.getCurrentCharacter().getCurrentSector()).contains(this.destinationSector)
		    && gameState.getTurn().getTurnState().equals(TurnState.STARTED))
			
			return true;
	  
			
			else return false;
			}
	
		

}
