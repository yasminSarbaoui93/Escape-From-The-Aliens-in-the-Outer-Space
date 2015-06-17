package it.polimi.ingsw.cg_5.controller;


import java.rmi.RemoteException;

import it.polimi.ingsw.cg_5.model.TurnState;
import it.polimi.ingsw.cg_5.view.SubscriberRmi;

import org.junit.Test;

public class GameManagerTest {

	@Test
	public void test() throws RemoteException {
GameManager prova = new GameManager();	
		SubscriberRmi subscriber = new SubscriberRmi("ANDREA");
		for(int i=0; i<11 ; i++){
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.getPlayerListManager().addToChosenList("FERMI",3, subscriber);
		prova.getPlayerListManager().addToChosenList("GALVANI",4, subscriber);
		}
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		
		prova.getPlayerListManager().addToChosenList("FERMI",3, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",4, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",5, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		
		
		prova.MatchCreator();
		prova.getPlayerListManager().addToChosenList("FERMI",3, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",4, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",5, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.getPlayerListManager().addToChosenList("GALILEI",8, subscriber);
		prova.MatchCreator();
		System.out.println(prova.getListOfMatch().get(1).getGameState().getMap().takeSector("L06").getCharacterList());
		
			
		for(WaitingList waitingList: prova.getPlayerListManager().getWaitingLists()){
		System.out.println(waitingList);
		
		}
		
	
		prova.getListOfMatch().get(1).getGameState().removeCharacter(prova.getListOfMatch().get(1).getGameState().getCharacterList().get(1));;
		prova.getListOfMatch().get(1).getGameState().goToNextCharacter();
		prova.getListOfMatch().get(1).getGameState().goToNextCharacter();
		prova.getListOfMatch().get(1).getGameState().currentCharacterDrawsItemCard();
		prova.getListOfMatch().get(1).getGameState().setGameDeck();
	}

}
