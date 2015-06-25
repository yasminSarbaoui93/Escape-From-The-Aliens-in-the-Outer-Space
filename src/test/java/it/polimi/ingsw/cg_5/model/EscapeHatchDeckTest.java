package it.polimi.ingsw.cg_5.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscapeHatchDeckTest {

	@Test
	public void test() {
		

		EscapeHatchDeck escapeHatchDeck = new EscapeHatchDeck();
		assertEquals(6, escapeHatchDeck.dimension());
		Card card = escapeHatchDeck.removeCard();
		Card card1 = escapeHatchDeck.removeCard();
		System.out.println(escapeHatchDeck.dimension());
		assertEquals(4, escapeHatchDeck.dimension());
		
		System.out.println(card);
		System.out.println(card1);
		System.out.println(escapeHatchDeck);
	}

}
