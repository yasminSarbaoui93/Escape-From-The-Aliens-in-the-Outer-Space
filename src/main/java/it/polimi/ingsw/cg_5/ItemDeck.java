package it.polimi.ingsw.cg_5;

import java.util.ArrayList;
import java.util.Collections;

public class ItemDeck extends Deck {
	
	// descrizione dell quantita' di Carta: adrenaline attack teleport spotlight sono 2 
	//sedatives sono 3 e defense 1
	private ArrayList<ItemCard> usedItemCard;
	private ArrayList<ItemCard> itemDeck;
	
	//  ritorna dimensione dell item deck
	
	public int dimension(){
		return itemDeck.size();
		
	}
	
	// crea item deck poi fa shuffle 
	public ItemDeck(){
		usedItemCard= new ArrayList<ItemCard>();
		itemDeck= new ArrayList<ItemCard>();
		
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
	// la carta rimossa dovra essere aggiunta alla lista usedItemDeck
	@Override
	public Card removeCard() {
	ItemCard currentCard = itemDeck.remove(itemDeck.size()-1);
	usedItemCard.add(currentCard);
	return currentCard;
	}

}
