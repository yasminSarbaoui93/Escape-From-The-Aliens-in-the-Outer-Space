package it.polimi.ingsw.cg_5.controller;

import java.util.Timer;


import it.polimi.ingsw.cg_5.model.EscapeSector;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.SafeSector;
import it.polimi.ingsw.cg_5.model.TurnState;
import it.polimi.ingsw.cg_5.model.Character;



public class EndTurn extends Action {
private Match match;
	public EndTurn(GameState gameState,Match match) {
		super(gameState);
	this.match=match;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.cg_5.controller.Action#execute()
	 * Controls all the conditions to end a turn. A turn can end after an attack, or after using a card of type gameCard or ItemCard.
	 */
	@Override
	public void execute() {
		
		gameState.goToNextCharacter();
		gameState.getTurn().setTurnState(TurnState.STARTED);
		
		if(match.isGameOver()){
			System.out.println("aaa");
			for(Character character: match.getGameState().getCharacterList()){
			match.getGameState().getWinners().add(character);
			}
			match.setMatchState(MatchState.ENDED);
			System.out.println(match.getGameState());
			}
			
		
		if(!match.isGameOver()){
			this.gameState.getTimer().cancel();
			this.gameState.getTimer().purge();
			taskTimer task= new taskTimer(this.match);
			this.gameState.setTimer(new Timer());
			this.gameState.getTimer().schedule(task, 15*1000);
			}
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
