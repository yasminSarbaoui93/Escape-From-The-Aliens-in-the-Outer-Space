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
			
			GameState stateprova= new GameState(playersID,"GALILEI");
			//preparo le condizioni per far si che attacco vada buon fine nel caso i primo giocatore e' alieno
			System.out.println(stateprova.getCurrentCharacter().getCurrentSector());
			stateprova.getTurn().setTurnState(TurnState.HASMOVED);
			System.out.println("il player attuale e un "+stateprova.getCurrentCharacter());
			Attack attacco=new Attack(stateprova);
			if(attacco.checkAction()){
				System.out.println("la check da dato true e quindi si attacca");
				attacco.execute();
				System.out.println(stateprova.getTurn().getTurnState());
			}else System.out.println("la check ha dato esito negativo");
			
			/*sposto il player in un settore e lo faccio attaccare a vuoto*/
			
			stateprova.getCurrentCharacter().setCurrentSector(stateprova.getMap().takeSector("W05"));
			stateprova.getMap().takeSector("W05").getCharacterList().add(stateprova.getCurrentCharacter());
			if(attacco.checkAction()){
				attacco.execute();
			}
			
			
			
	}

}
