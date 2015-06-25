package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.connection.broker.Broker;
import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;


public class Match {
	private final Integer numberGame;
	private GameState gameState;
	private MatchState matchState;
	private final Broker broker;
	

	public MatchState getMatchState() {
		return matchState;
	}


	public void setMatchState(MatchState matchState) {
		this.matchState = matchState;
	}


	/**Constructor of a new match that will have as attributes the new game state, the ID of the match and the broker that will send all the broadcast messages to the subscribers that belong to this match.
	 * @param gameState
	 * @param numberGame
	 * @param broker
	 */
	public Match(GameState gameState ,Integer numberGame, Broker broker){
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
	
	

	public Broker getBroker() {
		return broker;
	}


	@Override
	public String toString() {
		return "Match [numberGame=" + numberGame + "numero settori mappa "+ gameState.getMap().getSize()+ "]" + "\n";
	}
	
	
	
	/**Boolean to control if there are humans or aliens still alive. If so, the game will not be over; while if there are no more humans or no more aliens playng the game, or if the number of rounds reached the maximum value of 39, the game will be over.
	 * @return true if the game is over.
	 */
	public boolean isGameOver(){
		if(this.gameState.getNumberOfHumanAlive()==0){
			gameState.getWinners().addAll(gameState.getCharacterList());
			return true;
		}
		if(this.gameState.getRound()==40){
			for(Character character : gameState.getCharacterList()){
				if(character.getClass()==Alien.class){
				gameState.getWinners().add(character);
				}else gameState.getLosers().add(character);
			}
			return true;
		}
		if(this.gameState.getNumberOfAliensAlive()==0){
			gameState.getWinners().addAll(gameState.getCharacterList());
			return true;
		}
		//controllo se escapeHatch tutti rotti
		for(EscapeSector sector : gameState.getMap().getEscapeHatchList()){
			if(sector.isAvailable()){
			return false;
			}	
			}
		//se siamo arrivati a sto punto vuol dire che tutti gi escape sono rotti quindi gli alieni rimasti --> vincitori
		for(Character character : gameState.getCharacterList()){
			 if(character.getClass()==Alien.class){
			 gameState.getWinners().add(character);
				}
				else gameState.getLosers().add(character);
				}
			return true;

		
		// di base sarà return true, perchè tanto è obbligato a fare il ciclo foreach e se almeno uno non è rotto
		//ritornerà false, altrimenti se tutti sono rotti ritornerà true, il gioco è finito
		

	}
	
	

}
