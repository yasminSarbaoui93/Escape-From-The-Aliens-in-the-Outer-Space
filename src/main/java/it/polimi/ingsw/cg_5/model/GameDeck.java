package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
import java.util.Collections;

public class GameDeck extends Deck {
	private ArrayList<GameCard> gameDeck;
	
	//costruttore di GameDeck 10 NOISEYOUSECTORE E NOISEANYSECTOR  DI QUESTI 4 CON ITEMICON CIASCUNO.  5 CARTE SILENZIO
	public GameDeck(){
		gameDeck = new ArrayList<GameCard>();
		
		for(int i=0;i<6;i++){
			gameDeck.add(new GameCard(false,GameCardType.NOISE_YOUR_SECTOR));
			gameDeck.add(new GameCard(false,GameCardType.NOISE_ANY_SECTOR));
		}
		
		for(int j=0;j<4;j++){
			gameDeck.add(new GameCard(true,GameCardType.NOISE_YOUR_SECTOR));
			gameDeck.add(new GameCard(true,GameCardType.NOISE_ANY_SECTOR));
			
		}
		
		for(int a=0;a<5;a++){
			gameDeck.add(new GameCard(false,GameCardType.SILENCE));
			
		}
		
		//When creating the new deck, we shuffle it and then the cards will be drawn from the top
		Collections.shuffle(gameDeck);
	
	}
	
	@Override
	
	//Removes the last card of the deck as it would be drawn from the top
	public Card removeCard() {
		return gameDeck.remove(gameDeck.size()-1);
		
	}
	
	

	@Override
	public String toString() {
		return "GameDeck: "+gameDeck;
	}
	
	

}
