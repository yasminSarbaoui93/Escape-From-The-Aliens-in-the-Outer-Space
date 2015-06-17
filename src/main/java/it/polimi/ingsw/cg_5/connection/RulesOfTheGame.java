package it.polimi.ingsw.cg_5.connection;

import java.rmi.RemoteException;

import it.polimi.ingsw.cg_5.controller.GameManager;


public class RulesOfTheGame {

	GameManager gameManager;
	SubscriberInterface subscriber;
	

	public Integer SubscribeRequest (String choosenMap, int choosenMaxSize, String name) throws RemoteException {
		
		Integer yourId =gameManager.getPlayerListManager().addToChosenList(choosenMap, choosenMaxSize, subscriber);
		System.out.println("The player with ID:" + yourId + "joined the game");
		System.out.println("Matches started: " + gameManager.getListOfMatch());
		this.gameManager.MatchCreator();
		return yourId;
		
	}
}
