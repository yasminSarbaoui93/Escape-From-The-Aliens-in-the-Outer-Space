package it.polimi.ingsw.cg_5.controller;


import it.polimi.ingsw.cg_5.model.DangerousSector;
import it.polimi.ingsw.cg_5.model.GameCard;
import it.polimi.ingsw.cg_5.model.GameCardType;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.TurnState;

public class DrawCardFromGamedeck extends Action {

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

			System.out.println("mazzo ricreato");

		
		}
		// pesca Carta e vari comportamenti i base alla carta pescata
		GameCard drawnCard= (GameCard) gameState.getGameDeck().removeCard();
		
		if(drawnCard.getGameCardType()==GameCardType.NOISE_ANY_SECTOR){
			// chiedi al player settore dove si vuole muovere quando decide di andares su dangerous sector
			//sceglie anche il settore che bleffera'
			//cosi da rendere immaediata noyse_any_sector come noise_your_sector
			System.out.println("Noyse anysector");
			}
		if(drawnCard.getGameCardType()==GameCardType.NOISE_YOUR_SECTOR){
			//stampa a video settore del currentPlayer
			
			System.out.println("Noyse your sector");
		}
		
		if(drawnCard.getGameCardType()==GameCardType.SILENCE ){
			//stampa a tutti Silence
			System.out.println("silence");
		
		}
		//  dovro trovare un posto dove mettere il check che il deck  non sia vuoto- o se è vuoto controllare che usedItemDeck 
		//non sia vuoto
		if(drawnCard.isItemIcon()==true && checkItemDecks()){
			ItemCard DrawnitemCard =this.gameState.getItemDeck().removeCard();
			System.out.println("ha icona ");
			gameState.getCurrentCharacter().getItemPlayerCard().add(DrawnitemCard);
		}
		
		gameState.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		
		//ricordarsi del controlloche a fine turn il player deve avere al   max 3 CarteItem
		
	}
	//  il  giocatore puo' pescare solo se si e gia mossi e se si e in un Dangerous Sector
	public boolean checkAction(){
		if(gameState.getTurn().getTurnState()==TurnState.HASMOVED&&
				gameState.getCurrentCharacter().getCurrentSector().getClass()==DangerousSector.class)
			return true;
		
		return false;
		
	}
	
	// contrllo che nn siano vuoti itemdeck e useditemDeck
	public boolean checkItemDecks(){
		if(!gameState.getItemDeck().getItemDeck().isEmpty()|| !gameState.getItemDeck().getUsedItemDeck().isEmpty())
		return true;
		
		return false;
		
	}
	

}
