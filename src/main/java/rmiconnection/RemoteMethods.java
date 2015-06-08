package rmiconnection;

import java.rmi.*;



public interface RemoteMethods extends Remote {
	
	Integer SubscribeRequest(String choosenMap, int choosenMaxSize)throws RemoteException;
	String performMove(String sectorName, Integer yourId , Integer numberGame )throws RemoteException;
	String performAttack(Integer yourId ,Integer numberGame) throws RemoteException;
	
}