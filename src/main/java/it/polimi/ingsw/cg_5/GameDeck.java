package it.polimi.ingsw.cg_5;

import java.util.ArrayList;
import java.util.Collections;

public class GameDeck extends Deck {
	private ArrayList<GameCard> gameDeck;
	
	//costruttore di GameDeck
	public GameDeck(){
		gameDeck = new ArrayList<GameCard>();
		for(int i=0;i<5;i++){
		gameDeck.add(new Silence(false));
			
		}
		
		for(int j=0;j<6;j++){
			gameDeck.add(new NoiseYourSector(false));
			gameDeck.add(new NoiseAnySector(false));
		}
		
		for(int a=0;a<4;a++){
			gameDeck.add(new NoiseAnySector(true));
			gameDeck.add(new NoiseYourSector(true));
		}
		Collections.shuffle(gameDeck);
	}
	
	@Override
	public Card removeCard() {
		return gameDeck.remove(gameDeck.size()-1);
		//togliera una carta dal mazzo GameDeck
	}

}
