package it.polimi.ingsw.cg_5;

import static org.junit.Assert.*;

import org.junit.Test;

public class FractionTest {

	//vedere se l'oggetto creato ha un numeratore corretto
	
	@Test
	public void testGetNumerator() {
		int num = 3;
		int exp_num=3;
		Fraction f = new Fraction(num, 4);
		assertEquals(exp_num, f.getNumerator());
	}

}
