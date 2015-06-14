package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.EscapeSector;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.SafeSector;
import it.polimi.ingsw.cg_5.model.TurnState;



public class EndTurn extends Action {

	public EndTurn(GameState gameState) {
		super(gameState);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.cg_5.controller.Action#execute()
	 * Controls all the conditions to end a turn. A turn can end after an attack, or after using a card of type gameCard or ItemCard.
	 */
	@Override
	public void execute() {
		gameState.goToNextCharacter();
		gameState.getTurn().setTurnState(TurnState.STARTED);
	}
	
	public boolean checkAction(){
		if((gameState.getTurn().getTurnState()==TurnState.HASATTACKORDRAWN && gameState.getCurrentCharacter().getItemPlayerCard().size()<4)||
				gameState.getTurn().getTurnState()==TurnState.HASMOVED && gameState.getCurrentCharacter().getCurrentSector().getClass()==SafeSector.class
				|| gameState.getCurrentCharacter().getCurrentSector().getClass()==EscapeSector.class) 
			return true;
		else
		return false;
		
		
	}
	
	

}
