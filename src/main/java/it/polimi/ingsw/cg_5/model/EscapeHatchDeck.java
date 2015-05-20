package it.polimi.ingsw.cg_5;

import java.util.ArrayList;
import java.util.Collections;

public class EscapeHatchDeck extends Deck {

	private ArrayList<EscapeHatchCard> escapeHatchDeck;
	
	// costruttore escape Hatch deck
	public EscapeHatchDeck(){
		
		escapeHatchDeck= new ArrayList<EscapeHatchCard>();
		for(int i=0;i<3;i++){
		escapeHatchDeck.add(new EscapeHatchCard(EscapeHatchType.GREEN_SHALLOP));
		escapeHatchDeck.add(new EscapeHatchCard(EscapeHatchType.RED_SHALLOP));
		}
		
		Collections.shuffle(escapeHatchDeck);
		}
		//creara deck una carta alla volta 
		
	public int dimension(){
		return escapeHatchDeck.size();
	}
	
	@Override
	public Card removeCard() {
		return escapeHatchDeck.remove(escapeHatchDeck.size()-1);
	}

}
