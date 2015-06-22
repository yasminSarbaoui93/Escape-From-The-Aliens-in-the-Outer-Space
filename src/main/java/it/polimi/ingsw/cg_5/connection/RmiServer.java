package it.polimi.ingsw.cg_5.connection;
import it.polimi.ingsw.cg_5.controller.GameManager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer extends Server {

	private final Registry registry; 
	private static final String NAME = "room";
	private GameManager gameManager;
	
	public RmiServer ()  throws RemoteException {
		super();
		registry = LocateRegistry.createRegistry(1099);
		RemoteMethodsImpl remoteMethods1 = new RemoteMethodsImpl(gameManager);
		
		registry.rebind(NAME, remoteMethods1);
		System.out.println("Starting server, waiting for request...");
		
	}



}
