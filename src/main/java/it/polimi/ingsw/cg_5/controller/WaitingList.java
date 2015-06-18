package it.polimi.ingsw.cg_5.controller;


import it.polimi.ingsw.cg_5.view.User;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberInterfaceRmi;

import java.util.ArrayList;




public class WaitingList {
	private ArrayList <User> users = new ArrayList <User> ();
	private final String choosenMap;
	private final int maxSize;
	
	//private final int  MAX_NUM_OF_PLAYERS=8;
	

	public int getMaxSize() {
		return maxSize;
	}
	

	public WaitingList(User newUser, String choosenMap, int maxSize) {
		this.users.add(newUser);
		this.choosenMap=choosenMap;
		this.maxSize=maxSize;
			
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public ArrayList<Integer> getPlayersID() {
		ArrayList<Integer> PlayerIDList = new ArrayList<Integer> ();
		for ( User user : this.users ){
			PlayerIDList.add(user.getPlayerId());
		}
		return PlayerIDList ;
		
	}
	public ArrayList<SubscriberInterfaceRmi> getPlayersSubscriber() {
		ArrayList<SubscriberInterfaceRmi> PlayerSubscriberList = new ArrayList<SubscriberInterfaceRmi> ();
		for ( User user : this.users ){
			PlayerSubscriberList.add(user.getUserSubscriber());
		}
		return PlayerSubscriberList ;
		
	}
	
	

	public  void addToWaitingList(User newUser){
		users.add(newUser);
		
	
	}
	
	public String getChoosenMap() {
		return choosenMap;
	}
	
	public void removeFromWaitingList(User user){
		//N.B. VERIFICARE CHE VENGA CHIAMATA LA REMOVE CHE RIMUOVE L'OGGETTO E NON L'INDICE, VISTO CHE CI POTREBBE ESERE
		// AMBIGUITA' VISTO CHE GLI OGGETTI SONO INTEGER
		users.remove(user);
	}		
	
	public int getSize(){
		return users.size();
		}
	
	
	/**Boolean method that controls if the conditions for starting a new game are satisfied.
	 * @return True if the waiting list is full or if the time is over.
	 */
	public boolean canStartNewGame(){
		if(this.getSize()== this.maxSize) return true;
		else return false;
		
	}

	@Override
	public String toString() {
		return "WaitingList [Users=" + users + ", choosenMap="
				+ choosenMap + ", maxSize=" + maxSize + "]";
	}
	
	

}
