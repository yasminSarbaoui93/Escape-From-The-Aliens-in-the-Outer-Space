package it.polimi.ingsw.cg_5.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class GameStateTest {

	@Test
	public void test() {
ArrayList<Integer> players = new ArrayList<Integer>();
		
		for (int i=0 ; i<5; i++){
			players.add(i);
		}
		
		GameState prova2= new GameState (players, "Galilei");
		Iterator <Character> iteratore = prova2.getCharacterList().iterator();
		Character character = new Human("a",100);
		while (iteratore.hasNext()) {
			character=iteratore.next();			
			System.out.println("Il character "+ character.getName() + "  del giocatore con ID-" +character.getPlayerID()+
					" si trova nel settore " + character.getCurrentSector().toString());
			
			System.out.println("Inoltre il giocatore si potrà muovere nei seguenti settori: \n" +
			prova2.getMap().takeSector(character.getCurrentSector()
			.getSectorName()).getReachableSectors(character.getMaxMove(), character.getCurrentSector()) + "\n" );
					
				} 
			
		prova2.getCurrentCharacter().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		prova2.getCurrentCharacter().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		prova2.getCurrentCharacter().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		System.out.println(prova2.getCurrentCharacter().getItemPlayerCard());
		//// cambio turno giocatore
		Character oldCurrentCharacter = prova2.getCurrentCharacter();
		prova2.getCharacterList().remove(oldCurrentCharacter);
		prova2.setCurrentCharacter(prova2.getCharacterList().get(0));
		prova2.getCharacterList().add(oldCurrentCharacter);
		
		System.out.println(prova2.getCurrentCharacter().getItemPlayerCard());
		System.out.println(prova2.getCharacterList().get(4).getItemPlayerCard());
		prova2.getCurrentCharacter().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		prova2.getCurrentCharacter().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		System.out.println(prova2.getCurrentCharacter().getItemPlayerCard());

	}

}
