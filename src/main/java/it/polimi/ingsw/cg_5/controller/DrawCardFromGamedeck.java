package it.polimi.ingsw.cg_5.controller;


import it.polimi.ingsw.cg_5.model.DangerousSector;
import it.polimi.ingsw.cg_5.model.GameCard;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.TurnState;

public class DrawCardFromGamedeck extends Action {
	private GameCard drawnCard;
	
	public GameCard getDrawnCard() {
		return drawnCard;
	}

	public DrawCardFromGamedeck(GameState gameState) {
		super(gameState);

	}

	/* 
	 * It controls if there are still cards in the game deck. If so, it removes the last card from the game deck;
	 * if not, it creates a new game deck where to draw the cards.
	 */
	@Override
	public void execute() {
		// se il mazzo è vuoto lo ricrea .
		if(gameState.getGameDeck().getGameDeck().isEmpty()){

			gameState.setGameDeck();

		
		}
		// pesca Carta e vari comportamenti in base alla carta pescata
		drawnCard= (GameCard) gameState.currentCharacterDrawsGameCard();
		
		gameState.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		
	
		
	}
	//  il  giocatore puo' pescare solo se si e gia mossi e se si e in un Dangerous Sector
	public boolean checkAction(){
		if(gameState.getTurn().getTurnState()==TurnState.HASMOVED&&
				gameState.getCurrentCharacter().getCurrentSector().getClass()==DangerousSector.class)
			return true;
		
		return false;
		
	}
	
	// controllo che nn siano vuoti itemdeck e useditemDeck
	public boolean checkItemDecks(){
		if(!gameState.getItemDeck().getItemDeck().isEmpty()|| !gameState.getItemDeck().getUsedItemDeck().isEmpty())
		return true;
		
		return false;
		
	}
	

}
