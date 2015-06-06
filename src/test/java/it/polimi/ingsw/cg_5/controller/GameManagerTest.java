package it.polimi.ingsw.cg_5.controller;


import it.polimi.ingsw.cg_5.model.TurnState;

import org.junit.Test;

public class GameManagerTest {

	@Test
	public void test() {
GameManager prova = new GameManager();	
		
		for(int i=0; i<11 ; i++){
		prova.getPlayerListManager().addToChosenList("GALILEI",8);
		prova.getPlayerListManager().addToChosenList("FERMI",3);
		prova.getPlayerListManager().addToChosenList("GALVANI",4);
		}
		prova.getPlayerListManager().addToChosenList("GALILEI",8);
		prova.getPlayerListManager().addToChosenList("GALILEI",8);
		
		prova.getPlayerListManager().addToChosenList("FERMI",3);
		prova.getPlayerListManager().addToChosenList("GALILEI",4);
		prova.getPlayerListManager().addToChosenList("GALILEI",5);
		prova.getPlayerListManager().addToChosenList("GALILEI",8);
		prova.getPlayerListManager().addToChosenList("GALILEI",8);
		prova.getPlayerListManager().addToChosenList("GALILEI",8);
		prova.getPlayerListManager().addToChosenList("GALILEI",8);
		
		
		prova.MatchCreator();
			
		for(WaitingList waitingList: prova.getPlayerListManager().getWaitingLists()){
		System.out.println(waitingList);
		
		}
		
	
		prova.getListOfMatch().get(1).getGameState().removeCharacter(prova.getListOfMatch().get(1).getGameState().getCharacterList().get(1));;
		prova.getListOfMatch().get(1).getGameState().goToNextCharacter();
		prova.getListOfMatch().get(1).getGameState().goToNextCharacter();
		prova.getListOfMatch().get(1).getGameState().currentCaracterDrawsItemCard();
		prova.getListOfMatch().get(1).getGameState().setGameDeck();
	}

}
