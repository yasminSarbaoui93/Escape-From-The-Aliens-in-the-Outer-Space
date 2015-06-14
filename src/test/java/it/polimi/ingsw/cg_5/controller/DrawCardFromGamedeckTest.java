package it.polimi.ingsw.cg_5.controller;



import java.util.ArrayList;

import it.polimi.ingsw.cg_5.model.DangerousSector;
import it.polimi.ingsw.cg_5.model.GameState;

import it.polimi.ingsw.cg_5.model.TurnState;

import org.junit.Test;

public class DrawCardFromGamedeckTest {

	
	@Test
	public void test() {
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		for (int i=0 ; i<5; i++){
			playersID.add(i);
		}
		// per testare tutto dovrei impostare la carta pescata  
		//nel test ho messo settore del player dangerous ed impostato turnstate a hasmove.
		
		
		GameState stateprova= new GameState(playersID,"GALLILEI",0);
		stateprova.getCurrentCharacter().setCurrentSector(new DangerousSector("L05"));
		
		DrawCardFromGamedeck pescaCarta=new DrawCardFromGamedeck(stateprova);
		stateprova.getTurn().setTurnState(TurnState.HASMOVED);
		System.out.println(stateprova.getCurrentCharacter().getCurrentSector());
		System.out.println(pescaCarta.checkAction());
		pescaCarta.execute();
		// dopo aver pescato  il turnstate va a endTurn quindi il check ritornera false
		System.out.println(pescaCarta.checkAction());
		
		System.out.println("\n\nrimuovo tutte le carte del deck");
		for(int i=1;i<25;i++){
		stateprova.getGameDeck().removeCard();
		}
		
		System.out.println("lancio execute");
		pescaCarta.execute();
		
	}

}
