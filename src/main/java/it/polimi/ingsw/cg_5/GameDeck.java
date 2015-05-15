package it.polimi.ingsw.cg_5;

import java.util.ArrayList;

public class GameDeck extends Deck {
	private ArrayList<GameCard> gameDeck;
	
	//costruttore di GameDeck
	public GameDeck(){
		//creara deck una carta alla volta 
				//poi si fara shuffle del deck creato
		
	}
	
	@Override
	public Card removeCard() {
		return this.removeCard();
		//togliera una carta dal mazzo GameDeck
	}

}
