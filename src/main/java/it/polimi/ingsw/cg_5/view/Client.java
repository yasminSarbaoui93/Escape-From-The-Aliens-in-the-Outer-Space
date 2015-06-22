package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface Client {

	
	public Integer matchRequest(String stringa, Integer maxSize, String name, String connectionType) throws RemoteException, NotBoundException, UnknownHostException, IOException;
	
	public PlayerDTO moveRequest(String sector, Integer yourId, Integer gameNumber) throws RemoteException, ClassNotFoundException, IOException;
	
	public PlayerDTO attackRequest(Integer yourId, Integer gameNumber) throws RemoteException, ClassNotFoundException, IOException;
	
	public PlayerDTO endTurnRequest(Integer yourId, Integer gameNumber) throws RemoteException, ClassNotFoundException, IOException;
	
	public PlayerDTO drawCardRequest(Integer yourId, Integer gameNumber) throws RemoteException, ClassNotFoundException, IOException;
	
	public PlayerDTO useCardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws RemoteException, ClassNotFoundException, IOException;
	
	public PlayerDTO useSpotLightRequest(String itemCardType, Integer yourId, Integer gameNumber,String sector) throws RemoteException, ClassNotFoundException, IOException;
	
	public PlayerDTO bluffRequest(String bluffSector, Integer yourId, Integer gameNumber) throws RemoteException, ClassNotFoundException, IOException;
	
	public PlayerDTO discardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws RemoteException, ClassNotFoundException, IOException;

	
}
