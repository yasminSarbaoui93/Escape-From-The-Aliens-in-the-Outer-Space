package it.polimi.ingsw.cg_5.connection.broker;

import it.polimi.ingsw.cg_5.model.Character;

import java.rmi.RemoteException;

public interface PubSubCommunication {
	public void dispatchMessage(String msg) throws RemoteException;
	public void updateNumberGame(Integer numberGame) throws RemoteException;
	public void updateCharacter(Character character) throws IllegalArgumentException, RemoteException;
	public void updatecurrentPlayerId(int playerId) throws RemoteException;
}
