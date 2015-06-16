package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.controller.*;
import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;

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
		// NON SERVONO, LE CANCELLIAMO??
		//System.out.println(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
		//System.out.println(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());

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
							this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("Now is the turn of the Player"
									+ this.gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
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
				if(!attack.getPlayerToKill().isEmpty()){
				gameManager.getListOfMatch().get(numberGame).getBroker().publish("The players/s"+
				attack.getPlayerToKill() +" was/were killed by the player with ID- " + 
			  gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID() + "!");
				}
				if(!attack.getSafeCharacter().isEmpty()){
					gameManager.getListOfMatch().get(numberGame).getBroker().publish("The player "+ 
				attack.getSafeCharacter().get(0).getPlayerID() + "was attacked but he's alive thanks to the Defence Card");
				}
				this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("The player "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()+" has attacked.");
				if(gameManager.getListOfMatch().get(numberGame).isGameOver()){
					gameManager.getListOfMatch().get(numberGame).setMatchState(MatchState.ENDED);
					gameManager.getListOfMatch().get(numberGame).getBroker().publish("The match is ended!");
					}
				return "You attack!";
			}
			else{
				return " You cannot attack! Maybe you are an Human or you haven't draw yet!";
			}
		}
		else {
			return "You don't belong to any game ore it's not your turn";
		}
	}
	
	public String performEndTurn(Integer yourId,Integer numberGame)  throws RemoteException{
		if(gameManager.canAct(numberGame, yourId)){
			EndTurn endTurn = new EndTurn(gameManager.getListOfMatch().get(numberGame).getGameState());
			if(endTurn.checkAction()){		
				endTurn.execute();
				return "You end your turn!";
			}
			else 
				return "You can't finish your turn!Maybe you've to do some stuff or you Item deck has more than 4 cards!";
		}
		else return "You don't belong to any game ore it's not your turn!";
		
	}
	
	public String performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException{
		String message = new String ("");
		if(gameManager.canAct(numberGame, yourId)){
			DrawCardFromGamedeck drawCard = new DrawCardFromGamedeck(gameManager.getListOfMatch().get(numberGame).getGameState());
			if(drawCard.checkAction()){		
				drawCard.execute();
				if(drawCard.getDrawnCard().isItemIcon()==true ){
					if(drawCard.checkItemDecks()){
					Card itemCard= gameManager.getListOfMatch().get(numberGame).getGameState().currentCharacterDrawsItemCard();
				message = new String("The ItemIcon was true and you draw the Item Card: " + itemCard +"\n");
					}
				else {
					message = new String("The ItemIcon was true but the ItemDeck is finish! \n");
				}
					}
				if(drawCard.getDrawnCard().getGameCardType()==GameCardType.NOISE_YOUR_SECTOR){
					gameManager.getListOfMatch().get(numberGame).getBroker().publish(
							"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
							+" make noise in the Sector: " + gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getCurrentSector());
				}
				if(drawCard.getDrawnCard().getGameCardType()==GameCardType.SILENCE ){
					gameManager.getListOfMatch().get(numberGame).getBroker().publish("Silence...");
				
				}
				if(drawCard.getDrawnCard().getGameCardType()==GameCardType.NOISE_ANY_SECTOR ){
					gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().setTurnState(TurnState.BLUFFING);
					return message + "You draw the card :" + drawCard.getDrawnCard()+ " ... you may bluff a Sector!";	
				}
				return message + "You draw the card :" + drawCard.getDrawnCard();
			}
			else 
				return "You cannot draw";
		}
		else return "You don't belong to any game or it's not your turn!";
		
	}

	@Override
	public String bluffSector(String bluffSector, Integer yourId , Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			if(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState()==TurnState.BLUFFING){
			try {
			gameManager.getListOfMatch().get(numberGame).getBroker().publish(
					"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
					+" make noise in the Sector: " + gameManager.getListOfMatch().get(numberGame).getGameState().getMap().takeSector(bluffSector));
			gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
			return "Bluff Successful!";
			}
		catch(NullPointerException e){
			return e.getMessage();
		}
			}
			else return "You can't Bluff at the moment!";
		}
		else return "You don't belong to any game or it's not your turn!"; 
	}

	@Override
	public String performUseCard(String itemCardType, Integer yourId,
			Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			ItemCardType cardType= null;
			if(itemCardType.equals("ATTACK"))
				cardType=ItemCardType.ATTACK;
			if(itemCardType.equals("SEDATIVES"))
				cardType=ItemCardType.SEDATIVES;
			if(itemCardType.equals("TELEPORT"))
				cardType=ItemCardType.TELEPORT;
			if(itemCardType.equals("ADRENALINE"))
				cardType=ItemCardType.ADRENALINE;
			UseItemCard itemCard = new UseItemCard(gameManager.getListOfMatch().get(numberGame).getGameState(),
						cardType);
			if(itemCard.checkAction()){
				itemCard.execute();
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" use the Item Card " + itemCardType ) ;
				return "You use the Item Card" + itemCardType ;
			}
			else{
				return "You cannot use this card at the moment or you don't own this card!"; 
			}
			}
			
		 return "You don't belong to any game or it's not your turn!"; 
		
		
	}

	@Override
	public String performSpotLightUse(String itemCardType, Integer yourId,
			Integer numberGame, String sector) throws RemoteException {
		
		if(gameManager.canAct(numberGame, yourId)){
			ItemCardType cardType= null;
			if(itemCardType.equals("SPOTLIGHT")){
				cardType=ItemCardType.SPOTLIGHT;
			}
			UseSpotLight useSpotLight= new UseSpotLight(gameManager.getListOfMatch().get(numberGame).getGameState(),
					cardType,sector);
			if(useSpotLight.checkAction()){
				useSpotLight.execute();
				if( !useSpotLight.getSpottedPlayer().isEmpty()){
				for(Character character : useSpotLight.getSpottedPlayer()){
				gameManager.getListOfMatch().get(numberGame).getBroker().publish("The Player with ID -" + character.getPlayerID()
						+ "is in the Sector: " + character.getCurrentSector());
				}
				}
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" use the Item Card " + itemCardType ) ;
				return "You use the Item Card" + itemCardType ;
			}
			else{
				return "You cannot use this card at the moment or you don't own this card!"; 
			}
		}
		else return "You don't belong to any game or it's not your turn!"; 
		
	}

	@Override
	public String performDiscardCard(String itemCardType, Integer yourId,
			Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			ItemCardType cardType= null;
			if(itemCardType.equals("ATTACK"))
				cardType=ItemCardType.ATTACK;
			if(itemCardType.equals("SEDATIVES"))
				cardType=ItemCardType.SEDATIVES;
			if(itemCardType.equals("TELEPORT"))
				cardType=ItemCardType.TELEPORT;
			if(itemCardType.equals("ADRENALINE"))
				cardType=ItemCardType.ADRENALINE;
			if(itemCardType.equals("DEFENCE"))
				cardType=ItemCardType.DEFENCE;
			if(itemCardType.equals("SPOTLIGHT"))
				cardType=ItemCardType.SPOTLIGHT;
			DiscardItemCard discartCard = new DiscardItemCard(gameManager.getListOfMatch().get(numberGame).getGameState(),cardType);
			if(discartCard.checkAction()){
				discartCard.execute();
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" discard an Item Card of Type " + itemCardType ) ;
				return "You succesfully discard a card of type:"+itemCardType +"!";
			}
			else{
				return "You can't discard this Card!";
			}
		}
		else return "You don't belong to any game or it's not your turn!"; 
		
	}
	

	

}