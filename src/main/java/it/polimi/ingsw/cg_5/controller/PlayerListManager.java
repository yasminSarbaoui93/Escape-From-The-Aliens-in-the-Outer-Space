package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;

public class PlayerListManager {
private static int PlayerId=0;
private ArrayList <WaitingList> WaitingLists= new ArrayList <WaitingList>();

public ArrayList<WaitingList> getWaitingLists() {
	return WaitingLists;
}

public void addToChosenList(String choosenMap, int choosenMaxSize){	
	if(WaitingLists.isEmpty()){ 
		//SE LA LISTA VUOTA E' NON FACCIO ALTRO CHE CREARE UNA NUOVA WAITING LIST CON LA MAPPA SCELTA DAL PRIMO PLAYER
			WaitingList newWaitingList= new WaitingList(PlayerId,choosenMap,choosenMaxSize);
			PlayerId++;
			WaitingLists.add(newWaitingList);			
		}
	else if(!WaitingLists.isEmpty()) {	
		for(WaitingList waitingList : WaitingLists){				
		//SE LA LISTA DELLE WAITING LIST NON E' VUOTA CI SONO DUE CASI:
		// 1)C'E' UN ALTRA WAITING LIST CON STESSA MAPPA SCELTA E NUMERO DI GIOCATORI MASSIMO MINORE 
		//DI QUELLO DA ME SCELTO, IN TAL CASO  NON DEVO FARE ALTRO CHE AGGIUGERMI A QUELLA LISTA
			if(waitingList.getSize()<waitingList.getMaxSize() && choosenMap==waitingList.getChoosenMap()
					&& choosenMaxSize >= waitingList.getMaxSize()){
			waitingList.addToWaitingList(PlayerId);		
			PlayerId++;
			return; //SERVE PER TERMINARE QUI LA FUNZIONE, ALTRIMENTI SE TERMINA IL CICLO FOREACH CREO NUOVO GIOCO
			}
		}		
		//2) L'ALTRO CASO E' SE LA LISTA DELLE WAITING LIST NON E' VUOTA, MA NON C'E' UNA WAITING LIST 
		//  PRONTA AD ACCOGLIERMI, IN TAL CASO DOVRO' CREARE UNA NUOVA WAITING LIST
				WaitingList newWaitingList= new WaitingList(PlayerId,choosenMap,choosenMaxSize);
				PlayerId++;
				WaitingLists.add(newWaitingList);		
				
			
	}
		
	}
}
		




