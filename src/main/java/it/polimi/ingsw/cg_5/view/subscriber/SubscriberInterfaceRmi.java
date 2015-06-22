package it.polimi.ingsw.cg_5.view.subscriber;

import java.rmi.Remote;

import it.polimi.ingsw.cg_5.connection.broker.PubSubCommunication;
import it.polimi.ingsw.cg_5.model.Character;

import java.rmi.RemoteException;

public interface SubscriberInterfaceRmi extends Remote, Subscriber, PubSubCommunication{
	
	@Override
	public void dispatchMessage(String msg, Boolean chat) throws RemoteException;

	public void updateNumberGame(Integer numberGame) throws RemoteException;
	public void updateCharacter(Character character) throws RemoteException;
	public void updatecurrentPlayerId(int playerId) throws RemoteException;

}
