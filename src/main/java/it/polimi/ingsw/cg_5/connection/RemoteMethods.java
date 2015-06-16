package it.polimi.ingsw.cg_5.connection;

import java.rmi.*;




public interface RemoteMethods extends Remote {
	
	Integer SubscribeRequest(String choosenMap, int choosenMaxSize, String name)throws RemoteException,NotBoundException;
	PlayerDTO performMove(String sectorName, Integer yourId , Integer numberGame )throws RemoteException;
	PlayerDTO performAttack(Integer yourId ,Integer numberGame) throws RemoteException;
	PlayerDTO performEndTurn(Integer yourId,Integer numberGame) throws RemoteException;
	PlayerDTO performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException;
	PlayerDTO performUseCard(String itemCardType,Integer yourId,Integer numberGame)  throws RemoteException;
	PlayerDTO performSpotLightUse(String itemCardType,Integer yourId,Integer numberGame,String sector)  throws RemoteException;
	PlayerDTO bluffSector(String bluffSector,Integer yourId ,Integer numberGame)  throws RemoteException;
	PlayerDTO performDiscardCard(String itemCardType,Integer yourId,Integer numberGame)  throws RemoteException;
}
