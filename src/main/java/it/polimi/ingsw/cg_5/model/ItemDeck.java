package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
import java.util.Collections;

public class ItemDeck extends Deck {
	
	// descrizione dell quantita' di Carta: adrenaline attack teleport spotlight sono 2 
	//sedatives sono 3 e defense 1
	private ArrayList<ItemCard> usedItemDeck;
	private ArrayList<ItemCard> itemDeck;
	
	//  ritorna dimensione dell item deck
	
	public int getSize(){
		return itemDeck.size();
	}
	
	//Creates an empty array so that when i create it, it can be either the deck of discarded cards and the deck of itemCards 
	public ItemDeck(){
		itemDeck= new ArrayList<ItemCard>();
		usedItemDeck = new ArrayList<ItemCard>();

		for(int i=0;i<2;i++){
			itemDeck.add(new ItemCard(ItemCardType.ADRENALINE) );
			itemDeck.add(new ItemCard(ItemCardType.ATTACK ) );
			itemDeck.add(new ItemCard(ItemCardType.SPOTLIGHT) );
			itemDeck.add(new ItemCard(ItemCardType.TELEPORT) );
			
		}
		
		for(int j=0;j<3;j++){
			itemDeck.add(new ItemCard(ItemCardType.SEDATIVES) );
	
		}
		
		itemDeck.add(new ItemCard(ItemCardType.DEFENCE) );
		
		Collections.shuffle(itemDeck);
	}
	
	// la carta rimossa dovra essere aggiunta alla lista usedItemDeck e ritornata
	@Override
	public ItemCard removeCard() {
		return itemDeck.remove(itemDeck.size()-1);
		
	}
	
	public void addToUsedDeck(ItemCard currentCard){
		this.usedItemDeck.add(currentCard);
		
	}

	@Override
	public String toString() {

		return "ItemDeck [" + itemDeck + "]\n\nDeck Of Descard Cards ["+usedItemDeck+"]";
	}

}
