package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.*;
import java.rmi.server.*;

public class RemoteMethodsImpl extends UnicastRemoteObject implements RemoteMethods {
	GameManager gameManager ;

	protected RemoteMethodsImpl() throws RemoteException {	}
	private static final long serialVersionUID = 1L;
	public RemoteMethodsImpl(GameManager gameManager) throws RemoteException{
		this.gameManager= GameManager.getInstance();;
	}
	
	@Override
	public synchronized Integer SubscribeRequest (String choosenMap, int choosenMaxSize, String name, String connectionType) throws NotBoundException, UnknownHostException, IOException {
		Integer yourId = gameManager.getGameRules().SubscribeRequest(choosenMap, choosenMaxSize, name, connectionType);
		return yourId;
		/*Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		SubscriberInterfaceRmi subscriber = (SubscriberInterfaceRmi) registry.lookup(name);
		//SubscriberInterface subscriber = new SubscriberRmi(name);
		Integer yourId =gameManager.getPlayerListManager().addToChosenList(choosenMap, choosenMaxSize, subscriber);//PROBLEMS of remote exception
		System.out.println("The player with ID:" + yourId + "joined the game");
		System.out.println("Matches started: " + gameManager.getListOfMatch());
		this.gameManager.MatchCreator();
		return yourId;*/
		
	}
	
	@Override
	public PlayerDTO performMove(String sectorName, Integer yourId ,Integer numberGame) throws RemoteException {
		return gameManager.getGameRules().performMove(sectorName, yourId, numberGame);
	}
	
	@Override
	public PlayerDTO performAttack(Integer yourId ,Integer numberGame) throws RemoteException {
		return gameManager.getGameRules().performAttack(yourId, numberGame);
	}
	
	@Override
	public PlayerDTO performEndTurn(Integer yourId,Integer numberGame)  throws RemoteException{
		
		return gameManager.getGameRules().performEndTurn(yourId, numberGame);
	}
	
	@Override
	public PlayerDTO performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException{
		return gameManager.getGameRules().performDrawCard(yourId, numberGame);
	}

	@Override
	public PlayerDTO bluffSector(String bluffSector, Integer yourId , Integer numberGame) throws RemoteException {
			return gameManager.getGameRules().bluffSector(bluffSector, yourId, numberGame);
	}

	@Override
	public PlayerDTO performUseCard(String itemCardType, Integer yourId, Integer numberGame) throws RemoteException {
		return gameManager.getGameRules().performUseCard(itemCardType, yourId, numberGame);	
	}

	@Override
	public PlayerDTO performSpotLightUse(String itemCardType, Integer yourId, Integer numberGame, String sector) throws RemoteException {
		return gameManager.getGameRules().performSpotLightUse(itemCardType, yourId, numberGame, sector);	
	}

	@Override
	public PlayerDTO performDiscardCard(String itemCardType, Integer yourId,
			Integer numberGame) throws RemoteException {
		return gameManager.getGameRules().performDiscardCard(itemCardType, yourId, numberGame);
	}
	

	

}
