package it.polimi.ingsw.cg_5.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscapeHatchDeckTest {

	@Test
	public void test() {
		

		EscapeHatchDeck escapeHatchDeck = new EscapeHatchDeck();
		
		Card card = escapeHatchDeck.removeCard();
		Card card1 = escapeHatchDeck.removeCard();
		
			
		System.out.println(card);
		System.out.println(card1);
		System.out.println(escapeHatchDeck);
	}

}