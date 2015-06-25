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
		
		EscapeHatchCard card2 = new EscapeHatchCard(EscapeHatchType.RED_SHALLOP);
		assertEquals(true,card2.isRed());
		assertEquals(EscapeHatchType.RED_SHALLOP,card2.getEscapeHatchType());
		EscapeHatchCard card3 = new EscapeHatchCard(EscapeHatchType.GREEN_SHALLOP);
		assertEquals(true,card3.isGreen());
		
		System.out.println(card);
		System.out.println(card1);
		System.out.println(escapeHatchDeck);
	}

}
