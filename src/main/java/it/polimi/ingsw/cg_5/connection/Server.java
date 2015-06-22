package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.GameManager;

public abstract class Server {
		GameManager gameManager;
	
		public Server(){
		gameManager = GameManager.getInstance();
	//capisce in automatico se la connessione sara di tipo rmi o socket a seconda di quelli che arriva in ingresso
	}

}