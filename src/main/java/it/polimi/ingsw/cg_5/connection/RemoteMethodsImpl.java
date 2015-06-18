package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.*;
import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberInterfaceRmi;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class RemoteMethodsImpl extends UnicastRemoteObject implements RemoteMethods {
	GameManager gameManager ;

	protected RemoteMethodsImpl() throws RemoteException {	}
	private static final long serialVersionUID = 1L;
	GameRules gameRules;
	public RemoteMethodsImpl(GameManager gameManager, GameRules gameRules) throws RemoteException{
		this.gameManager=gameManager;
		this.gameRules = gameRules;
	}
	
	@Override
	public synchronized Integer SubscribeRequest (String choosenMap, int choosenMaxSize, String name) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		SubscriberInterfaceRmi subscriber = (SubscriberInterfaceRmi) registry.lookup(name);
		//SubscriberInterface subscriber = new SubscriberRmi(name);
		Integer yourId =gameManager.getPlayerListManager().addToChosenList(choosenMap, choosenMaxSize, subscriber);
		System.out.println("The player with ID:" + yourId + "joined the game");
		System.out.println("Matches started: " + gameManager.getListOfMatch());
		this.gameManager.MatchCreator();
		return yourId;
		
	}
	
	@Override
	public PlayerDTO performMove(String sectorName, Integer yourId ,Integer numberGame) throws RemoteException {
		return gameRules.performMove(sectorName, yourId, numberGame);
	}
	
	@Override
	public PlayerDTO performAttack(Integer yourId ,Integer numberGame) throws RemoteException {
		return gameRules.performAttack(yourId, numberGame);
	}
	
	@Override
	public PlayerDTO performEndTurn(Integer yourId,Integer numberGame)  throws RemoteException{
		
		return gameRules.performEndTurn(yourId, numberGame);
	}
	
	@Override
	public PlayerDTO performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException{
		return gameRules.performDrawCard(yourId, numberGame);
	}

	@Override
	public PlayerDTO bluffSector(String bluffSector, Integer yourId , Integer numberGame) throws RemoteException {
			return gameRules.bluffSector(bluffSector, yourId, numberGame);
	}

	@Override
	public PlayerDTO performUseCard(String itemCardType, Integer yourId, Integer numberGame) throws RemoteException {
		return gameRules.performUseCard(itemCardType, yourId, numberGame);	
	}

	@Override
	public PlayerDTO performSpotLightUse(String itemCardType, Integer yourId, Integer numberGame, String sector) throws RemoteException {
		return gameRules.performSpotLightUse(itemCardType, yourId, numberGame, sector);	
	}

	@Override
	public PlayerDTO performDiscardCard(String itemCardType, Integer yourId,
			Integer numberGame) throws RemoteException {
		return gameRules.performDiscardCard(itemCardType, yourId, numberGame);
	}
	

	

}
