package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import it.polimi.ingsw.cg_5.model.*;


public class GameManager extends Observable{
		private static int indexOfCurrentMatches=0;
	private HashMap <Integer , Match> listOfMatch= new HashMap <Integer, Match> () ;
	private PlayerListManager playerListManager =new PlayerListManager();
	
	/**Method that creates a new match of the game. The conditions to respect are mainly two: the waiting list of a certain game is full; the timer reaches the maximum waiting time set.
	 * 
	 */
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
	
	
	/**Controls if the player that sends a request for doing a certain action is in the list of player of the match and if it's his turn.
	 * @param numberGame
	 * @param playerID
	 * @return true if the player's ID is in the list of current players of a certain match.
	 */
	public boolean canAct(Integer numberGame,Integer playerID){
		if(this.listOfMatch.containsKey(numberGame)){
				if(this.listOfMatch.get(numberGame).getGameState().getCurrentCharacter().getPlayerID()==playerID)
					return true;		
			}
	
		return false;
		
	}
	
	
}
