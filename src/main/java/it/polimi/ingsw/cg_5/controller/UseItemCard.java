package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.ItemCardType;
import it.polimi.ingsw.cg_5.model.TurnState;

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
	public boolean checkCharacterItemDeck(){
		if(gameState.getCurrentCharacter().getItemPlayerCard().contains(usingItemCardType))
		return true;
		
		return false;
		
	}

}
