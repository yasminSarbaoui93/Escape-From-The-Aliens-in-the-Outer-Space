package it.polimi.ingsw.cg_5.controller;



import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.TurnState;

import java.util.ArrayList;

import org.junit.Test;

public class AttackTest {

	@Test
	public void test() {
		
		
			ArrayList<Integer> playersID = new ArrayList<Integer>();
			
			for (int i=0 ; i<8; i++){
				playersID.add(i);
			}
			
			GameState stateprova= new GameState(playersID,"GALILEI",0);
			//preparo le condizioni per far si che attacco vada buon fine nel caso i primo giocatore e' alieno
			System.out.println(stateprova.getCurrentCharacter().getCurrentSector());
			stateprova.getTurn().setTurnState(TurnState.HASMOVED);
			System.out.println("il player attuale e un "+stateprova.getCurrentCharacter());
			Attack attacco=new Attack(stateprova);
			
			if(attacco.checkAction()){
				System.out.println("la check da dato true e quindi si attacca");
				attacco.execute();
				
				if(attacco.getCharacterToKill().size()==0)
					System.out.println("attacco e' andato  a vuoto");
				else
					System.out.println("i players attaccati sono"+attacco.getCharacterToKill());
				
				System.out.println(stateprova.getTurn().getTurnState());
			}else System.out.println("la check ha dato esito negativo");
			
			/*sposto il player in un settore e lo faccio attaccare a vuoto*/
			System.out.println("\n ooooh");
			stateprova.getTurn().setTurnState(TurnState.HASMOVED);
			attacco.getCharacterToKill().clear();
			//risetto turno has moved
			stateprova.getCurrentCharacter().setCurrentSector(stateprova.getMap().takeSector("J05"));
			stateprova.getMap().takeSector("J05").getCharacterList().add(stateprova.getCurrentCharacter());
			if(attacco.checkAction()){
				attacco.execute();
				if(attacco.getCharacterToKill().size()==0)
					System.out.println("attacco e' andato  a vuoto");
				else
					System.out.println("ho attaccato i player"+attacco.getCharacterToKill());
				
			}else System.out.println("check false");
			
			
			
	}

}
