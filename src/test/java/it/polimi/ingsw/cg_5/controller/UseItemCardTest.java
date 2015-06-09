package it.polimi.ingsw.cg_5.controller;


import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCardType;

import java.util.ArrayList;

import org.junit.Test;

public class UseItemCardTest {

	@Test
	public void test() {
   ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		for (int i=0 ; i<5; i++){
			playersID.add(i);
		}
		GameState stateprova= new GameState(playersID,"GALLILEI");
		
		System.out.println("uso una carta presente nel mazzo");
		for(int i =0;i<8;i++){
		stateprova.getCurrentCharacter().getItemPlayerCard().add(stateprova.getItemDeck().removeCard());
		}
		System.out.println(stateprova.getCurrentCharacter().getClass());
		System.out.println(stateprova.getCurrentCharacter().getItemPlayerCard());
		
		String sectorToSpotlight=stateprova.getMap().takeSector("N08").getSectorName();
		UseItemCard cartaAdrenalina= new UseItemCard(stateprova, ItemCardType.ADRENALINE, sectorToSpotlight);
		
		
		if(cartaAdrenalina.checkAction()){
			cartaAdrenalina.execute();
		}
		
		System.out.println(stateprova.getCurrentCharacter().getItemPlayerCard());
		
		
		
		
	}
}


