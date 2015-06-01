package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.*;


public class Match {
	private final Integer numberGame;
	private GameState gameState;
	private MatchState matchState;
	
	
	public MatchState getMatchState() {
		return matchState;
	}


	public void setMatchState(MatchState matchState) {
		this.matchState = matchState;
	}


	public Match(GameState gameState ,int numberGame){
		this.numberGame=numberGame;
		this.gameState=gameState;
		this.matchState=MatchState.RUNNING;
	}
	

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public int getNumberGame() {
		return numberGame;
	}

	@Override
	public String toString() {
		return "Match [numberGame=" + numberGame + "numero settori mappa "+ gameState.getMap().getSize()+ "]" + "\n";
	}
	
	
	//  controllo se gli umani siano fniti
	public boolean isGameOver(){
		if(this.gameState.getNumberOfHumanAlive()!=0)
		return false;
		
		return true;
		
	}
	
	

}
