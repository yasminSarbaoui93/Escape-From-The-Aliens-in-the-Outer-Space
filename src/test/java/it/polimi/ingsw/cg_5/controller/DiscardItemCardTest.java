package it.polimi.ingsw.cg_5.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.ItemCardType;

import java.util.ArrayList;

import org.junit.Test;

public class DiscardItemCardTest {

	@Test
	public void test() {
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		
		for (int i=0 ; i<5; i++){
			playersID.add(i);
		}
		ItemCard itemcard= new ItemCard(ItemCardType.ADRENALINE);
		GameState matchGalilei1= new GameState (playersID, "Galilei",0);
		//aggiungo carta a un mazzo del giocatore e verifico che venga scartata!
		matchGalilei1.getCurrentCharacter().getItemPlayerCard().add(itemcard);
		assert(matchGalilei1.getCurrentCharacter().getItemPlayerCard().contains(itemcard));
		DiscardItemCard discardCard= new DiscardItemCard(matchGalilei1,ItemCardType.ADRENALINE);
		discardCard.execute();
		assert(!matchGalilei1.getCurrentCharacter().getItemPlayerCard().contains(itemcard));
	}

}
