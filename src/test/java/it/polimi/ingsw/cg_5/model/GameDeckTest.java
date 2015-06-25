package it.polimi.ingsw.cg_5.model;

//import static org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameDeckTest {

	@Test
	public void drawTest() {
		
		//Create and print a new deck of Game Cards
		GameDeck gameDeck = new GameDeck();
		System.out.println(gameDeck+"\n");
		
		//Remove a card and control that it's not removing always the same one
		//Control even if the number of cards in the deck is decrementing
		for(int i=0; i<20; i++){
			Card card = gameDeck.removeCard();
			System.out.println("Drawn Card: "+card);
		}
		
		//prova carte
		GameCard game = new GameCard(true, GameCardType.NOISE_YOUR_SECTOR);
		assertEquals(true,game.isItemIcon());
		assertEquals(GameCardType.NOISE_YOUR_SECTOR,game.getGameCardType());
		
		System.out.println("\n"+gameDeck);
		
		
	}

}
