package it.polimi.ingsw.cg_5.view;


import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.connection.RemoteMethods;
import java.io.Serializable;
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
	public Integer matchRequest(String stringa, Integer maxSize, String name) throws RemoteException, NotBoundException{
		return remoteMethods1.SubscribeRequest(stringa, maxSize, name);
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

}
