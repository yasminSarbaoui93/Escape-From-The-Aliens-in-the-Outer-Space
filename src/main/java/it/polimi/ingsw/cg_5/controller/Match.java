package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;


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
	
	
	
	/**Boolean to control if there are still humans or aliens still alive. If so, the game will not be over; while if there are no more humans or no more aliens playng the game, or if the number of rounds reached the maximum value of 39, the game will be over.
	 * @return true if the game is over.
	 */
	public boolean isGameOver(){
		if(this.gameState.getNumberOfHumanAlive()!=0)
			return false;
		if(this.gameState.getRound()<40)
			return false;
		if(this.gameState.getNumberOfAliensAlive()!=0)
			return false;
		
		return true;
		
	}
	
	

}
