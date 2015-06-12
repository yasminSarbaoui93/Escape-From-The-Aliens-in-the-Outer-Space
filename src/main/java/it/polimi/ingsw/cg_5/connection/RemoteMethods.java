package it.polimi.ingsw.cg_5.connection;

import java.rmi.*;




public interface RemoteMethods extends Remote {
	
	Integer SubscribeRequest(String choosenMap, int choosenMaxSize)throws RemoteException;
	void startNewMatch() throws RemoteException;
	String performMove(String sectorName, Integer yourId , Integer numberGame )throws RemoteException;
	String performAttack(Integer yourId ,Integer numberGame) throws RemoteException;
	String performEndTurn(Integer yourId,Integer numberGame) throws RemoteException;
	String performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException;
}