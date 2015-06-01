package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;

public class WaitingList {
	private ArrayList <Integer> playersID = new ArrayList <Integer> ();
	private final String choosenMap;
	private final int maxSize;
	private final int  MAX_NUM_OF_PLAYERS=8;
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public WaitingList(int playerID, String choosenMap, int maxSize) {
		this.playersID.add(playerID);
		this.choosenMap=choosenMap;
		this.maxSize=maxSize;
	}
	
	public ArrayList<Integer> getPlayersID() {
		return playersID;
	}

	public  void addToWaitingList(Integer PlayersID){
		playersID.add(PlayersID);
	}
	
	public String getChoosenMap() {
		return choosenMap;
	}
	
	public void removeFromWaitingList(Integer PlayersID){
		//N.B. VERIFICARE CHE VENGA CHIAMATA LA REMOVE CHE RIMUOVE L'OGGETTO E NON L'INDICE, VISTO CHE CI POTREBBE ESERE
		// AMBIGUITA' VISTO CHE GLI OGGETTI SONO INTEGER
		playersID.remove(PlayersID);
	}		
	
	public int getSize(){
		return playersID.size();
		}
	public boolean canStartNewGame(){
		if(this.getSize()== this.maxSize) return true;
		else return false;
		
	}

	@Override
	public String toString() {
		return "WaitingList [playersID=" + playersID + ", choosenMap="
				+ choosenMap + ", maxSize=" + maxSize + "]";
	}
	
	

}
