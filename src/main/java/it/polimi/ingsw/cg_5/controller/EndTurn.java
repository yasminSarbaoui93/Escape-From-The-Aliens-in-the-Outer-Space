package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.GameState;

public class EndTurn extends Action {

	public EndTurn(GameState gameState) {
		super(gameState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		//cambia il current player del gameState
		
		
		
		// nel caso che laa index del player sia = all ultima posizione  della lista di character il round verra aumentato
	}
	
	public boolean checkEndTurn(){
		//se siamo al turnState.ENDTURN possiamo eseguirlo
		// nel caso che laa index del player sia = all ultima posizione  della lista di character il round verra aumentato
		return false;
		
		
	}

}
