package it.polimi.ingsw.cg_5.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ItemDeckTest {

	
	@Test
	public void test() {
		ItemDeck itemDeck = new ItemDeck();
		
		//Creation of an arrayList where to put used cards (that players decide not to use or to use)
	
		
		for(int i=0; i<5; i++){
		ItemCard card = itemDeck.removeCard();
		//Adding the discarded card to the new deck of discarded item cards
		itemDeck.addToUsedDeck(card);
		
		}
		
		System.out.println(itemDeck);
		
	}

}
