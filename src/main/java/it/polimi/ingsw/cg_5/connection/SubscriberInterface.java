package it.polimi.ingsw.cg_5.connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubscriberInterface extends Remote {
	
	public void dispatchMessage(String msg) throws RemoteException;
	public void updateNumberGame(int numberGame) throws RemoteException;
}
