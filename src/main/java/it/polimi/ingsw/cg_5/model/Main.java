package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		
ArrayList<Integer> players = new ArrayList<Integer>();
		
		for (int i=0 ; i<5; i++){
			players.add(i);
		}
		
		
		GameState prova2= new GameState (players,"GALILEI");

		prova2.getMap().printMap();
	
	}


	}


