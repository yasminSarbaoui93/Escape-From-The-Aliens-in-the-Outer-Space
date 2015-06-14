package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.*;
import it.polimi.ingsw.cg_5.model.EscapeHatchType;
import it.polimi.ingsw.cg_5.model.EscapeSector;
import it.polimi.ingsw.cg_5.model.Sector;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class RemoteMethodsImpl extends UnicastRemoteObject implements RemoteMethods {
	GameManager gameManager ;

	protected RemoteMethodsImpl() throws RemoteException {	}
	private static final long serialVersionUID = 1L;
	
	public RemoteMethodsImpl(GameManager gameManager) throws RemoteException{
		this.gameManager=gameManager;
	}
	
	@Override
	public synchronized Integer SubscribeRequest (String choosenMap, int choosenMaxSize, String name) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		
		// CI POTREBBE ESSERE UN PROBLEMA SE DUE GIOCATORI FANNO RICHIESTA NELLO STESSO ISTANTE USANDO LO STESSO USER
		//INOLTRE USANDO USER DOVREMO FAR SI CHE SIA UNIVOCO, INTRODUCENDO QUINDI LA REGISTRAZIONE DEGLI USER
		SubscriberInterface subscriber = (SubscriberInterface) registry.lookup(name);
		Integer yourId =gameManager.getPlayerListManager().addToChosenList(choosenMap, choosenMaxSize, subscriber);
		
		
		System.out.println("Il giocatore con ID:" + yourId + "è stato aggiunto!");
		System.out.println("Giochi partiti: " + gameManager.getListOfMatch());
		
		//SIAMO NEL SERVER
		//fai qui la subscribe
		//lancio il matchCreator 
		this.gameManager.MatchCreator();
		return yourId;
		
	}
	
	@Override
	public String performMove(String sectorName, Integer yourId ,Integer numberGame) throws RemoteException {
		System.out.println(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
		System.out.println(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());

		try{
			if(gameManager.canAct(numberGame, yourId)){
				Sector destinationSector=gameManager.getListOfMatch().get(numberGame).getGameState().getMap().takeSector(sectorName);
				if(destinationSector.getClass() != EscapeSector.class){
					Move move = new Move(gameManager.getListOfMatch().get(numberGame).getGameState(),destinationSector); 
					if(move.checkAction()){
						move.execute();
						return "You moved onto the selected "+ destinationSector.getClass().toString() +" sector";
					}else return "You cannot move onto that sector.";
				}
				else {
					EscapeMove runAway = new EscapeMove(gameManager.getListOfMatch().get(numberGame).getGameState(), destinationSector);
					if(runAway.checkAction()){
						runAway.execute();
						if(runAway.getEscapeCard().getEscapeHatchType()==EscapeHatchType.GREEN_SHALLOP){
							return "Since you ran away, you won the match. CONGRATULATIONS!!!";
						}
						else{
							return "You destroyed the shallop. Look for other escape hatch.";
						}
						
					}else return "You cannot move onto that sector.";
				
				}
				
			}
			else
				return "You don't belong to any game ore it's not your turn";
		}catch(NullPointerException e){
			return e.getMessage();
		}
	}
	
	
	public String performAttack(Integer yourId ,Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			Attack attack = new Attack(gameManager.getListOfMatch().get(numberGame).getGameState());
			if(attack.checkAction()){
				attack.execute();
				this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("The player "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()+" has attacked.");
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