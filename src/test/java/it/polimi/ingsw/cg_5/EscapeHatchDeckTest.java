package it.polimi.ingsw.cg_5;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscapeHatchDeckTest {

	@Test
	public void test() {
		EscapeHatchDeck deck = new EscapeHatchDeck();
		System.out.println(deck.dimension());
		assertEquals(6, deck.dimension());
		System.out.println(deck.removeCard());
		System.out.println(deck.dimension());
	}

}
