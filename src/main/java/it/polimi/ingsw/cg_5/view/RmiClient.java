package it.polimi.ingsw.cg_5.view;


import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.connection.RemoteMethods;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RmiClient implements Serializable, Client {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NAME = "room";
	private final static String HOST="127.0.0.1"; 
	private final static int PORT=1099;
	private RemoteMethods remoteMethods1;

	
	/**Gets the registry created by the Rmi server and makes the lookup of the remothe methods put in it.
	 * @throws Exception
	 */
	public RmiClient () throws Exception{		
		try{
			Registry registry = LocateRegistry.getRegistry(HOST,PORT);
			remoteMethods1 = (RemoteMethods) registry.lookup(NAME); 
			
		}
		catch( Exception e){
		e.printStackTrace();
		//System.err.println("Connessione Fallita!");
		}
	}
	
	@Override
	public Integer matchRequest(String stringa, Integer maxSize, String name, String connectionType) throws NotBoundException, UnknownHostException, IOException{
		return remoteMethods1.SubscribeRequest(stringa, maxSize, name, connectionType);
	}
	@Override
	public PlayerDTO moveRequest(String sector, Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performMove(sector, yourId,gameNumber);
	}
	@Override
	public PlayerDTO attackRequest(Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performAttack(yourId,gameNumber);
	}
	@Override
	public PlayerDTO endTurnRequest(Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performEndTurn(yourId,gameNumber);
	}
	@Override
	public PlayerDTO drawCardRequest(Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performDrawCard(yourId, gameNumber);
	}
	@Override
	public PlayerDTO useCardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performUseCard(itemCardType,yourId, gameNumber);
	}
	@Override
	public PlayerDTO useSpotLightRequest(String itemCardType, Integer yourId, Integer gameNumber,String sector) throws RemoteException {
		return remoteMethods1.performSpotLightUse(itemCardType,yourId, gameNumber,sector);
	}
	@Override
	public PlayerDTO bluffRequest(String bluffSector, Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.bluffSector(bluffSector,yourId, gameNumber);
	}
	@Override
	public PlayerDTO discardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performDiscardCard(itemCardType,yourId, gameNumber);
	}

	@Override
	public Void sendmessageRequest(String message, Integer yourId, Integer gameNumber) throws RemoteException {
		remoteMethods1.performSendMessage(message, yourId, gameNumber);
		return null;
	}

}
