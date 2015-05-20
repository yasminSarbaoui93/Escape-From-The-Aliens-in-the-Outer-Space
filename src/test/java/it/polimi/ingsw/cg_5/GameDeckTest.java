package it.polimi.ingsw.cg_5;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_5.model.GameDeck;

import org.junit.Test;

public class GameDeckTest {

	@Test
	public void test(){
		
		GameDeck gameD = new GameDeck();
		
		for(int i=0; i< 25;i++)
		System.out.println(gameD.removeCard()+"\n");

}
}