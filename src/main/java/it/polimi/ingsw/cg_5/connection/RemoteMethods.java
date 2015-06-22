package it.polimi.ingsw.cg_5.connection;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.*;




public interface RemoteMethods extends Remote {
	
	Integer SubscribeRequest(String choosenMap, int choosenMaxSize, String name, String connectionType)throws RemoteException,NotBoundException, UnknownHostException, IOException;
	PlayerDTO performMove(String sectorName, Integer yourId , Integer numberGame )throws RemoteException;
	PlayerDTO performAttack(Integer yourId ,Integer numberGame) throws RemoteException;
	PlayerDTO performEndTurn(Integer yourId,Integer numberGame) throws RemoteException;
	PlayerDTO performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException;
	PlayerDTO performUseCard(String itemCardType,Integer yourId,Integer numberGame)  throws RemoteException;
	PlayerDTO performSpotLightUse(String itemCardType,Integer yourId,Integer numberGame,String sector)  throws RemoteException;
	PlayerDTO bluffSector(String bluffSector,Integer yourId ,Integer numberGame)  throws RemoteException;
	PlayerDTO performDiscardCard(String itemCardType,Integer yourId,Integer numberGame)  throws RemoteException;
	void performSendMessage(String message, Integer yourId,Integer gameNumber)throws RemoteException;
}
