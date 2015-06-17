package it.polimi.ingsw.cg_5.connection;

import java.rmi.Remote;
import it.polimi.ingsw.cg_5.model.Character;
import java.rmi.RemoteException;

public interface SubscriberInterface extends Remote{
	
	public void dispatchMessage(String msg) throws RemoteException;
	public void updateNumberGame(int numberGame) throws RemoteException;
	public void updateCharacter(Character character) throws RemoteException;
}
