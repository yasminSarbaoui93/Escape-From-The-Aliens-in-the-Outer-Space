package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.model.ItemCardType;

import java.rmi.*;




public interface RemoteMethods extends Remote {
	
	Integer SubscribeRequest(String choosenMap, int choosenMaxSize, String name)throws RemoteException,NotBoundException;
	String performMove(String sectorName, Integer yourId , Integer numberGame )throws RemoteException;
	String performAttack(Integer yourId ,Integer numberGame) throws RemoteException;
	String performEndTurn(Integer yourId,Integer numberGame) throws RemoteException;
	String performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException;
	String performUseCard(String itemCardType,Integer yourId,Integer numberGame)  throws RemoteException;
	String performSpotLightUse(String itemCardType,Integer yourId,Integer numberGame,String sector)  throws RemoteException;
	String bluffSector(String bluffSector,Integer yourId ,Integer numberGame)  throws RemoteException;
}