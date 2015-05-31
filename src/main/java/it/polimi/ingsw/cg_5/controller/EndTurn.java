package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.TurnState;

public class EndTurn extends Action {

	public EndTurn(GameState gameState) {
		super(gameState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		gameState.goToNextCharacter();
		gameState.getTurn().setTurnState(TurnState.STARTED);
	}
	
	public boolean checkEndTurn(){
		if(gameState.getTurn().getTurnState()==TurnState.HASATTACKORDRAWN) 
			return true;
		else
		return false;
		
		
	}

}
