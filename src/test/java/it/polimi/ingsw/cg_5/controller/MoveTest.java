package it.polimi.ingsw.cg_5.controller;



import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.Sector;
import it.polimi.ingsw.cg_5.model.TurnState;

import java.util.ArrayList;

import org.junit.Test;

public class MoveTest {

	@Test
	public void test() {
		
	ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		for (int i=0 ; i<5; i++){
			playersID.add(i);
		}
		// problema primo player che gioca e sempre human .. va rimischiato primadi assegnare turni
		
		GameState stateprova= new GameState(playersID,"GALILEI",0);
		System.out.println("Provo a spostare il current character in L09,funzionerà solo se human");
		Sector destination = stateprova.getMap().takeSector("L09");
		Move mossaValida= new Move(stateprova, destination);
		System.out.println("LA CHECK DA "+mossaValida.checkAction());
		if(mossaValida.checkAction()){
		mossaValida.execute();
		}
		System.out.println("IL SETTORE ATTUALE DEL PLAYER  "+stateprova.getCurrentCharacter().getCurrentSector());
		System.out.println("lo stato attuale del gioco è: " + stateprova.getTurn().getTurnState());
		
	// ora provo a check in un settore in cui non si puo' muoovere
		System.out.println("Se è alieno è ancora in alien Start, si potrà muoverre in L04, altrimenti vorrà dire che"
				+ " è umano e che si è già mosso \n , e quindi la check darà false");
		Sector destination2 = stateprova.getMap().takeSector("L04");
		Move mossaAlieno= new Move(stateprova, destination2);
		System.out.println("LA CHECK DA "+mossaAlieno.checkAction());
		if(mossaAlieno.checkAction()){
			mossaAlieno.execute();
		}
		System.out.println("IL SETTORE ATTUALE DEL PLAYER  "+stateprova.getCurrentCharacter().getCurrentSector());
		System.out.println("lo stato attuale del gioco è: " + stateprova.getTurn().getTurnState());
		
		
		System.out.println("metto il personaggio su settore all'interno dei confini del confine del escape 2 e lo muovo");
		stateprova.getTurn().setTurnState(TurnState.STARTED);
		stateprova.getCurrentCharacter().setCurrentSector(stateprova.getMap().takeSector("U02"));
		Sector destination3=stateprova.getMap().takeSector("V02");
		Move mossaEscape= new Move(stateprova,destination3);
		System.out.println(mossaEscape.checkAction());
	}

}
