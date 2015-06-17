package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.GameManager;

public abstract class Server {
	protected static GameManager gameManager;
	protected static GameRules gameRules;
	public Server(){
		gameManager = new GameManager();
		gameRules = new GameRules(gameManager);
		
	}
	//capisce in automatico se la connessione sara di tipo rmi o socket a seconda di quelli che arriva in ingresso
	
}
