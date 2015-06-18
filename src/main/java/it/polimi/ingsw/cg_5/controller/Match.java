package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;

import it.polimi.ingsw.cg_5.connection.broker.BrokerRmi;
import it.polimi.ingsw.cg_5.model.*;


public class Match {
	private final Integer numberGame;
	private GameState gameState;
	private MatchState matchState;
	private final BrokerRmi broker;


	public MatchState getMatchState() {
		return matchState;
	}


	public void setMatchState(MatchState matchState) {
		this.matchState = matchState;
	}


	public Match(GameState gameState ,Integer numberGame, BrokerRmi broker){
		this.numberGame=numberGame;
		this.gameState=gameState;
		this.matchState=MatchState.RUNNING;
		this.broker = broker;
		this.broker.setTopic("Game number: "+numberGame);
	
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
	
	

	public BrokerRmi getBroker() {
		return broker;
	}


	@Override
	public String toString() {
		return "Match [numberGame=" + numberGame + "numero settori mappa "+ gameState.getMap().getSize()+ "]" + "\n";
	}
	
	
	
	/**Boolean to control if there are still humans or aliens still alive. If so, the game will not be over; while if there are no more humans or no more aliens playng the game, or if the number of rounds reached the maximum value of 39, the game will be over.
	 * @return true if the game is over.
	 */
	public boolean isGameOver(){
		if(this.gameState.getNumberOfHumanAlive()==0)
			return true;
		if(this.gameState.getRound()==40)
			return true;
		if(this.gameState.getNumberOfAliensAlive()==0)
			return true;
		//controllo se escapeHatch tutti rotti
		ArrayList <EscapeSector> escapeSectorToCheck= new ArrayList <EscapeSector>() ;
		for(Integer i=1 ; i<5 ; i++){
			escapeSectorToCheck.add((EscapeSector)this.gameState.getMap().takeSector(i.toString()));
		}
		for(EscapeSector sector : escapeSectorToCheck){
			if(sector.isAvailable()){
			return false;
			}
			
		}
		// di base sarà return true, perchè tanto è obbligato a fare il ciclo foreach e se almeno uno non è rotto
		//ritornerà false, altrimenti se tutti sono rotti ritornerà true, il gioco è finito
		return true;
		
	}
	
	

}
