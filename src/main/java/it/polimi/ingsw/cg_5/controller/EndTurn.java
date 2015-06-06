package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.GameState;
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
		if(gameState.getTurn().getTurnState()==TurnState.HASATTACKORDRAWN) 
			return true;
		else
		return false;
		
		
	}
	// fare controllo prima di di fare end turn .
		public boolean playerCardSize(){
			if(gameState.getCurrentCharacter().getItemPlayerCard().size()>3)
			return false;
			
			else
				return true;
			
		}

}
