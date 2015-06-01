package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.Alien;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.ItemCardType;


public class UseItemCard extends Action {
	
	ItemCardType usingItemCardType;
	public UseItemCard(GameState gameState,ItemCardType itemCardType) {
		super(gameState);
		this.usingItemCardType=itemCardType;
		
		
	}

	@Override
	public void execute() {
		

		
		if(usingItemCardType==ItemCardType.ADRENALINE){
			gameState.getCurrentCharacter().setMaxMove(2);
			System.out.println("settatoMaxMove=2");
			
		}
		
		if(usingItemCardType==ItemCardType.ATTACK){
			// settera un attributo da qualche parte a true
		}
		
		if(usingItemCardType==ItemCardType.SEDATIVES){
			//permette di non pescare
			
		}
		
		if(usingItemCardType==ItemCardType.SPOTLIGHT){
			// nell interfaccia oltre a usare la carta verra detto il settore da spottare
		}
		
		if(usingItemCardType==ItemCardType.TELEPORT){
			
		}
	
		if(usingItemCardType==ItemCardType.DEFENCE){
			//messaggio non e possibile usarla  questo momento
			
		}
		
			
		
	
	}
	
	/**Controls if the character can use the declared item card. If it's a Human, it can use the card only if it has it in the array list of possessed item cards;
	 * while if it's an Alien, it can never use them, but only hold them; false if the character is an alien or if the human wants to use a card he doesn't haveee
	 * @return True if the character is a Human and he holds the card he wants to use
	 */
	
	//se e' true la check e true la carta viene rimossa poi succesivamente verra chiamata la remove
	public boolean checkItemDeck(){
		if(gameState.getCurrentCharacter().getClass() == Alien.class){
			return false;
		}
		else{
			for(ItemCard playerItemCard : gameState.getCurrentCharacter().getItemPlayerCard()){
				if(playerItemCard.getItemCardType().equals(usingItemCardType)){
					gameState.getCurrentCharacter().getItemPlayerCard().remove(playerItemCard);
					return true;
				}
			}
		}
		
		return false;
	}

}
