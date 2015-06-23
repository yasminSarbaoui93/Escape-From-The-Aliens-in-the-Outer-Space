package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.EscapeHatchCard;
import it.polimi.ingsw.cg_5.model.EscapeHatchType;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.Sector;
import it.polimi.ingsw.cg_5.model.TurnState;
import it.polimi.ingsw.cg_5.model.Character;

public class EscapeMove extends Move {
	EscapeHatchCard escapeCard=null;
    public EscapeHatchCard getEscapeCard() {
		return escapeCard;
	}


	Character escapedCharacter;
	
	public EscapeMove(GameState gameState, Sector destinationSector) {
		super(gameState, destinationSector);
	}
	

	@Override
	public void execute(){
		gameState.getCurrentCharacter().getCurrentSector().getCharacterList().
		remove(gameState.getCurrentCharacter());
		
		gameState.getMap().takeSector(destinationSector.getSectorName()).getCharacterList()
			.add(gameState.getCurrentCharacter());
		gameState.getCurrentCharacter().setCurrentSector(destinationSector);
		gameState.getTurn().setTurnState(TurnState.HASMOVED);
		
		escapeCard=(EscapeHatchCard) gameState.getEscapeHatchDeck().removeCard();
			
		
		if(escapeCard.getEscapeHatchType()==EscapeHatchType.GREEN_SHALLOP){
			escapedCharacter=gameState.getCurrentCharacter();
			gameState.goToNextCharacter();
			gameState.getCharacterList().remove(escapedCharacter);
			gameState.getTurn().setTurnState(TurnState.STARTED);	

		}
		gameState.destroyShallop(destinationSector);
			
			
	}
}
