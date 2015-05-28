package it.polimi.ingsw.cg_5.model;

public class Turn {
	private TurnState turnState;
	private int playerTurn;
	
	public Turn(TurnState turnState, int playerTurn){
		this.turnState = turnState;
		this.playerTurn = playerTurn;
	}
	
	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public TurnState getTurnState() {
		return turnState;
	}

	public void setTurnState(TurnState turnState) {
		this.turnState = turnState;
	}
}
