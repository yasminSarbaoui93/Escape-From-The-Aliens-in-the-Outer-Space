package it.polimi.ingsw.cg_5.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_5.model.DangerousSector;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.Sector;

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
		
		GameState stateprova= new GameState(playersID,"GALLILEI");
		System.out.println("INSERISCO settore valido L09 per  l'human");
		Sector destination = stateprova.getMap().takeSector("L09");
		Move mossaValida= new Move(stateprova, destination);
		System.out.println("LA CHECK DA "+mossaValida.checkMove());
		mossaValida.execute();
		
		System.out.println("IL SETTORE ATTUALE DEL PLAYER"+stateprova.getCurrentCharacter().getCurrentSector());
		
	// ora provo a check in un settore in cui non si puo' muoovere
		System.out.println("inseriscosettore w09 non valido e faccio check");
		Sector destination2 = stateprova.getMap().takeSector("W09");
		Move mossaNonValida= new Move(stateprova, destination2);
		System.out.println("LA CHECK DA "+mossaNonValida.checkMove());
		
		
		
		
		
	}

}
