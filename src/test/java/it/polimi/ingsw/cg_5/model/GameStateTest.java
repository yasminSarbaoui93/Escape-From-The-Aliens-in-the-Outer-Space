package it.polimi.ingsw.cg_5.model;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class GameStateTest {

	@Test
	public void test() {
String players []=new String[5];
		
		for (int i=0 ; i<5; i++){
			players[i]="Giocatore"+i;
		}
		
		GameState prova2= new GameState (players);
		Iterator <Player> iteratore = prova2.getPlayerList().iterator();
		Player player = new Player("a");
		while (iteratore.hasNext()) {
			player=iteratore.next();			
			System.out.println(player.getNickName() + "\n" );
			System.out.println("Il character "+ player.getPlayerCharacter().getName() + " si trova nel settore " +
					 player.getPlayerCharacter().getCurrentSector().toString());
			
			System.out.println("Inoltre il giocatore si potrà muovere nei seguenti settori: \n" +
			prova2.getMap().takeSector(player.getPlayerCharacter().getCurrentSector()
			.getSectorName()).getReachableSectors(2, player.getPlayerCharacter().getCurrentSector()) + "\n" );
					
				}
			
		prova2.getCurrentPlayer().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		prova2.getCurrentPlayer().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		prova2.getCurrentPlayer().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		System.out.println(prova2.getCurrentPlayer().getItemPlayerCard());
		//// cambio turno giocatore
		Player oldCurrentPlayer = prova2.getCurrentPlayer();
		prova2.getPlayerList().remove(oldCurrentPlayer);
		prova2.setCurrentPlayer(prova2.getPlayerList().get(0));
		prova2.getPlayerList().add(oldCurrentPlayer);
		
		System.out.println(prova2.getCurrentPlayer().getItemPlayerCard());
		System.out.println(prova2.getPlayerList().get(4).getItemPlayerCard());
		prova2.getCurrentPlayer().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		prova2.getCurrentPlayer().getItemPlayerCard().add(prova2.getItemDeck().removeCard());
		System.out.println(prova2.getCurrentPlayer().getItemPlayerCard());

	}

}