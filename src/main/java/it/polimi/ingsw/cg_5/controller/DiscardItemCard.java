package it.polimi.ingsw.cg_5.controller;


import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.ItemCardType;

public class DiscardItemCard extends Action {
	ItemCardType discardItemType;
	
	public DiscardItemCard(GameState gameState,ItemCardType discardItemType) {
		super(gameState);
		this.discardItemType=discardItemType;
		
	}

	@Override
	public void execute() {
		System.out.println("il current player ha rimosso una carta");

	}

	public boolean checkAction(){
		if(gameState.getCurrentCharacter().getItemPlayerCard().size()>3){
			for(ItemCard playerItemCard : gameState.getCurrentCharacter().getItemPlayerCard()){
				if(playerItemCard.getItemCardType().equals(discardItemType)){
					gameState.getCurrentCharacter().getItemPlayerCard().remove(playerItemCard);
					gameState.getItemDeck().getUsedItemDeck().add(playerItemCard);
					return true;
				}
			}
		}
			return false;
		}
		
	}


