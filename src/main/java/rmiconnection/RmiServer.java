package rmiconnection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import it.polimi.ingsw.cg_5.controller.*;

public class RmiServer {
	GameManager gameManager=new GameManager();
	private final Registry registry; 
	private static final String NAME = "room";
	
	public RmiServer ()  throws RemoteException {
		registry = LocateRegistry.createRegistry(1099);
		RemoteMethodsImpl remoteMethods1 = new RemoteMethodsImpl(gameManager);
		
		registry.rebind(NAME, remoteMethods1);
		System.out.println("Starting server, waiting for request...");
		
	}

	
	public static void main(String args[]) throws Exception{
		new RmiServer();
		
		
	}

}
