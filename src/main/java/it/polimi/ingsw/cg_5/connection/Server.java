package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.GameManager;

public abstract class Server {
		GameManager gameManager;
	
		public Server(){
		gameManager = GameManager.getInstance();
		}

}