package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.view.Subscriber;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class PlayerListManager {
private int PlayerId=0;
private Registry registry;

public void setRegistry(Registry registry) throws RemoteException{
	this.registry = registry;
}



private ArrayList <WaitingList> WaitingLists= new ArrayList <WaitingList>();

public ArrayList<WaitingList> getWaitingLists() {
	return WaitingLists;
}

/**This method adds the player that makes the request for playing to a waiting list. If there's already a waiting list that reflects the request of the client, then this one will be added to the existing waitng list.
 * If not, the method will create a new waiting list of a certain map and with the specified maximum number of players.
 * @param choosenMap
 * @param choosenMaxSize
 * @throws RemoteException 
 */
public Integer addToChosenList(String choosenMap, int choosenMaxSize, Subscriber subscriber) throws RemoteException{	
	
	if(WaitingLists.isEmpty()){ 
		
			WaitingList newWaitingList= new WaitingList(PlayerId,choosenMap,choosenMaxSize, registry);
			
			PlayerId++;
			WaitingLists.add(newWaitingList);	
		}
	else if(!WaitingLists.isEmpty()) {	
		for(WaitingList waitingList : WaitingLists){				

			if(waitingList.getSize()<waitingList.getMaxSize() && choosenMap.equals(waitingList.getChoosenMap())
					&& choosenMaxSize >= waitingList.getMaxSize()){
			waitingList.addToWaitingList(PlayerId);		
			PlayerId++;
			return PlayerId-1; //SERVE PER TERMINARE QUI LA FUNZIONE, ALTRIMENTI SE TERMINA IL CICLO FOREACH CREO NUOVO GIOCO
			}
		}		
		
				WaitingList newWaitingList= new WaitingList(PlayerId,choosenMap,choosenMaxSize, registry);
				PlayerId++;
				WaitingLists.add(newWaitingList);		
				
			
	}
	return PlayerId-1;
		
	}
}
		




