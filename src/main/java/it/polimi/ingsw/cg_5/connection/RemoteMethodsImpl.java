package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.*;

import java.rmi.*;
import java.rmi.server.*;

public class RemoteMethodsImpl extends UnicastRemoteObject implements RemoteMethods {
	GameManager gameManager ;

	protected RemoteMethodsImpl() throws RemoteException {	}
	private static final long serialVersionUID = 1L;
	
	public RemoteMethodsImpl(GameManager gameManager) throws RemoteException{
		this.gameManager=gameManager;
	}
	
	@Override
	public Integer SubscribeRequest(String choosenMap, int choosenMaxSize) throws RemoteException {
		Integer yourId =gameManager.getPlayerListManager().addToChosenList(choosenMap, choosenMaxSize);
		System.out.println("Il giocatore con ID:" + yourId + "è stato aggiunto!");
		System.out.println("Giochi partiti: " + gameManager.getListOfMatch());
		
		return yourId;
		
	}
	
	@Override
	public void startNewMatch() throws RemoteException {
		this.gameManager.MatchCreator();
	}

	@Override
	public String performMove(String sectorName, Integer yourId ,Integer numberGame) throws RemoteException {
		System.out.println(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
		System.out.println(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
		try{if(gameManager.canAct(numberGame, yourId)){
			Move move = new Move(gameManager.getListOfMatch().get(numberGame).getGameState(), 
					gameManager.getListOfMatch().get(numberGame).getGameState().getMap().takeSector(sectorName));
			if(move.checkAction()){
				System.out.println(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter()+
						"si è mosso con successo in" + sectorName);
				move.execute();
				return "Ti sei mosso con successo";
			}
			else{
				return " Non puoi muoverti in tal settore";
			}
		}
		else {
			return "Non è il tuo turno o non sei iscritto a nessun gioco!";
		}
		}
		catch(NullPointerException e){
			return e.getMessage();
			
		}
	}
	
	public String performAttack(Integer yourId ,Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			Attack attack = new Attack(gameManager.getListOfMatch().get(numberGame).getGameState());
			if(attack.checkAction()){
				System.out.println(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter()+
						"ha attaccato");
				attack.execute();
				return "Hai attaccato con successo!";
			}
			else{
				return " Non puoi attaccare! Ti tocca pescare!";
			}
		}
		else {
			return "Non è il tuo turno o non sei iscritto a nessun gioco!";
		}
	}
	
	public String performEndTurn(Integer yourId,Integer numberGame)  throws RemoteException{
		if(gameManager.canAct(numberGame, yourId)){
			EndTurn endTurn = new EndTurn(gameManager.getListOfMatch().get(numberGame).getGameState());
			if(endTurn.checkAction()){		
				endTurn.execute();
				return "hai finito il turno";
			}
			else 
				return "non puoi finire il turno!";
		}
		else return "Non è il tuo turno o non sei iscritto a nessun gioco!";
		
	}
	
	public String performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException{
		if(gameManager.canAct(numberGame, yourId)){
			DrawCardFromGamedeck drawCard = new DrawCardFromGamedeck(gameManager.getListOfMatch().get(numberGame).getGameState());
			if(drawCard.checkAction()){		
				drawCard.execute();
				return "hai pescato con successo";
			}
			else 
				return "non puoi pescare";
		}
		else return "Non è il tuo turno o non sei iscritto a nessun gioco!";
		
	}
	

	

}