package it.polimi.ingsw.cg_5.model;


import java.util.HashSet;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		/*
		Map prova= new Map();
		prova.Generator();
		prova.printMap();
		prova.AddBorders();
		prova.map.get("A03").bordersPrint();
		HashSet <Sector> provalista = new HashSet <Sector> ();
		provalista = prova.takeSector("A02").getReachableSectors(1);
		System.out.println(provalista);
		provalista = prova.takeSector("A02").getReachableSectors(2);
		System.out.println(provalista);
		provalista = prova.takeSector("A02").getReachableSectors(3);
		System.out.println(provalista); 
		*/
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
			.getSectorName()).getReachableSectors(2) + "\n" );
					
				}
			
			
}
}
