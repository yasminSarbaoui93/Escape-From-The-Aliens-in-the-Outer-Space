package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.cg_5.model.*;

public class GameManager {
		private static int indexOfCurrentMatches=0;
	private HashMap <Integer , Match> listOfMatch= new HashMap <Integer, Match> () ;
	private PlayerListManager playerListManager =new PlayerListManager();
	
	public void MatchCreator(){
		ArrayList <WaitingList> waitingListToRemove= new ArrayList <WaitingList>() ;
		for(WaitingList waitingList : playerListManager.getWaitingLists()){
			if(waitingList.canStartNewGame()){
				ArrayList <Integer> lista = waitingList.getPlayersID();
				System.out.println(lista);
				GameState newGameState=new GameState(lista,waitingList.getChoosenMap());
				Match newMatch =new Match(newGameState ,indexOfCurrentMatches);
				listOfMatch.put(indexOfCurrentMatches,newMatch);
				waitingListToRemove.add(waitingList);
				System.out.println("Ho Creato un nuovo Match");
				indexOfCurrentMatches++;
			}
		}
		playerListManager.getWaitingLists().removeAll(waitingListToRemove); // rimuovo tutte le liste di giochi partiti
		
				
	}

	public HashMap<Integer,Match> getListOfMatch() {
		return listOfMatch;
	}
	
	public Match takeMatch(int numberGame){
		return listOfMatch.get(numberGame);
	}
	

	public PlayerListManager getPlayerListManager() {
		return playerListManager;
	}
	
	
	
}
