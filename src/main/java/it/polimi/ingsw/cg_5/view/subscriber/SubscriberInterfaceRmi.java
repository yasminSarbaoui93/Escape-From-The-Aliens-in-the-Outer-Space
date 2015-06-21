package it.polimi.ingsw.cg_5.view.subscriber;

import java.rmi.Remote;
import it.polimi.ingsw.cg_5.model.Character;
import java.rmi.RemoteException;

public interface SubscriberInterfaceRmi extends Remote, Subscriber{
	
	public void dispatchMessage(String msg) throws RemoteException;
	public void updateNumberGame(int numberGame) throws RemoteException;
	public void updateCharacter(Character character) throws RemoteException;
	public void updatecurrentPlayerId(int playerId) throws RemoteException;
}
