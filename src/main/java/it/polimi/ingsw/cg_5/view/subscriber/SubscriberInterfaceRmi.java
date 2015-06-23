package it.polimi.ingsw.cg_5.view.subscriber;

import java.rmi.Remote;

import it.polimi.ingsw.cg_5.connection.broker.PubSubCommunication;
import it.polimi.ingsw.cg_5.model.Character;

import java.rmi.RemoteException;

public interface SubscriberInterfaceRmi extends Remote, Subscriber, PubSubCommunication{
	
	@Override
	public void dispatchMessage(String msg) throws RemoteException;

	@Override
	public void updateNumberGame(Integer numberGame) throws RemoteException;
	
	@Override
	public void updateCharacter(Character character) throws RemoteException;
	
	@Override
	public void updatecurrentPlayerId(int playerId) throws RemoteException;

}
