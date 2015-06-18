package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.GameManager;

import java.rmi.RemoteException;

public class MainServer {


	public static void main(String [] args) throws RemoteException{
	
		GameManager gameManager = new GameManager();
		new RmiServer(gameManager);
		new SocketServer(1337, gameManager);
		
	}
	

}
