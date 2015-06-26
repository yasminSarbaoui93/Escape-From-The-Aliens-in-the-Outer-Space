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
		drawnCard= (GameCard) gameState.currentCharacterDrawsGameCard();
		//gameState.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.cg_5.controller.Action#checkAction()
	 * The player can draw only if he already moved and he's on a dangerous sector
	 */
	@Override
	public boolean checkAction(){
		if(gameState.getTurn().getTurnState()==TurnState.HASMOVED&&
				gameState.getCurrentCharacter().getCurrentSector().getClass()==DangerousSector.class)
			return true;
		
		return false;
		
	}
	
	
	/**Checks if the item deck and used item deck are not empty
	 * @return
	 */
	public boolean checkItemDecks(){
		if(!gameState.getItemDeck().getItemDeck().isEmpty()|| !gameState.getItemDeck().getUsedItemDeck().isEmpty())
		return true;
		
		return false;
		
	}
	

}
