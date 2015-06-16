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
		
		
		System.out.println("The player with ID:" + yourId + "joined the game");
		System.out.println("Matches started: " + gameManager.getListOfMatch());
		this.gameManager.MatchCreator();
		return yourId;
		
	}
	
	@Override
	public PlayerDTO performMove(String sectorName, Integer yourId ,Integer numberGame) throws RemoteException {
		
		
		try{
			
			if(gameManager.canAct(numberGame, yourId)){
				PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
				Sector destinationSector=gameManager.getListOfMatch().get(numberGame).getGameState().getMap().takeSector(sectorName);
				if(destinationSector.getClass() != EscapeSector.class){
					Move move = new Move(gameManager.getListOfMatch().get(numberGame).getGameState(),destinationSector); 
					if(move.checkAction()){
						move.execute();
						playerDTO.getYourCharacter().setCurrentSector(destinationSector);
						playerDTO.setMessageToSend("You moved onto the sector "+ destinationSector);
						return playerDTO;
					}else {
						playerDTO.setMessageToSend("You cannot move onto that sector.");
						return playerDTO;
					}
				}
				else {
					EscapeMove runAway = new EscapeMove(gameManager.getListOfMatch().get(numberGame).getGameState(), destinationSector);
					if(runAway.checkAction()){
						runAway.execute();
						playerDTO.getYourCharacter().setCurrentSector(destinationSector);
						if(runAway.getEscapeCard().getEscapeHatchType()==EscapeHatchType.GREEN_SHALLOP){
							this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("Now is the turn of the Player"
									+ this.gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
							playerDTO.setMessageToSend("Since you ran away, you won the match. CONGRATULATIONS!!!");
							return playerDTO;
						}
						else{
							playerDTO.setMessageToSend("You destroyed the shallop. Look for other escape hatch.");
							return playerDTO;
						}
						
					}else{
						playerDTO.setMessageToSend("You cannot move onto that sector.");
						return playerDTO;
					}
				
				}
				
			}
			else{			
				PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game ore it's not your turn");	
				return playerDTO;				
			}
				
		}catch(NullPointerException e){
			PlayerDTO playerDTO= new PlayerDTO("Impossibile to find your character");
			return playerDTO;
		}
	}
	
	
	public PlayerDTO performAttack(Integer yourId ,Integer numberGame) throws RemoteException {
		
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
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
				playerDTO.setMessageToSend("You've attacked!!");
				return playerDTO;
			}
			else{
				playerDTO.setMessageToSend("You cannot attack! Maybe you are a Human or you haven't drawn yet");
				return playerDTO;
			}
		}
		else {
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn");
			return playerDTO;
		}
	}
	
	public PlayerDTO performEndTurn(Integer yourId,Integer numberGame)  throws RemoteException{
		
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			EndTurn endTurn = new EndTurn(gameManager.getListOfMatch().get(numberGame).getGameState());
			if(endTurn.checkAction()){		
				endTurn.execute();

				playerDTO.setMessageToSend("Your turn's over!");
				return playerDTO;

			}
			else 
				playerDTO.setMessageToSend("You can't end your turn yet! Check if you have to do something else or if your item deck has more than 3 cards!");
				return playerDTO;
		}

		else{
			PlayerDTO playerDTO = new PlayerDTO("It's not your turn or you didn't subscribe to any match yet");
			return playerDTO;
		}
		
	}
	
	public PlayerDTO performDrawCard(Integer yourId,Integer numberGame)  throws RemoteException{
		String message = new String ("");
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			DrawCardFromGamedeck drawCard = new DrawCardFromGamedeck(gameManager.getListOfMatch().get(numberGame).getGameState());
			if(drawCard.checkAction()){		
				drawCard.execute();
				if(drawCard.getDrawnCard().isItemIcon()==true ){
					if(drawCard.checkItemDecks()){
						Card itemCard= gameManager.getListOfMatch().get(numberGame).getGameState().currentCharacterDrawsItemCard();
						playerDTO.getYourCharacter().getItemPlayerCard().add((ItemCard)itemCard);
						message = "The ItemIcon was true and you draw the Item Card: " + itemCard +"\n";
					}
					else {
						message = "The ItemIcon was true but the ItemDeck has no more cards! \n";
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

					playerDTO.setMessageToSend(message+" You've drawn the card: "+drawCard.getDrawnCard()+"..you can bluff your position!");
					gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().setTurnState(TurnState.BLUFFING);
					return playerDTO;	
				}
				playerDTO.setMessageToSend(message+" You've drawn the card: "+drawCard.getDrawnCard());
				return playerDTO;
			}
			else{
				playerDTO.setMessageToSend("You cannot draw!!!");
				return playerDTO;
			}
		}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			return playerDTO;
		}
		
	}

	@Override
	public PlayerDTO bluffSector(String bluffSector, Integer yourId , Integer numberGame) throws RemoteException {
			if(gameManager.canAct(numberGame, yourId)){
				PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			if(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState()==TurnState.BLUFFING){
			try {
			gameManager.getListOfMatch().get(numberGame).getBroker().publish(
					"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
					+" make noise in the Sector: " + gameManager.getListOfMatch().get(numberGame).getGameState().getMap().takeSector(bluffSector));
			gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
			playerDTO.setMessageToSend("You bluffed succesfully!");
			return playerDTO;
			}
		catch(NullPointerException e){
			playerDTO.setMessageToSend(e.getMessage());
			return playerDTO;
		}
			}
			else{
				playerDTO.setMessageToSend("You can't bluff at the moment!");
				return playerDTO;
			}
		}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			return playerDTO; 
		}
	}

	@Override
	public PlayerDTO performUseCard(String itemCardType, Integer yourId, Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
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
				
				//CAZZATA PER RICORDARMI DI RIMUOVERE LA CARTA USATA DALL'ITEM DECK DEL PLAYER ATTUALE
				playerDTO.getYourCharacter().getItemPlayerCard().remove(cardType);
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" use the Item Card " + itemCardType ) ;
				playerDTO.setMessageToSend("You've used the item card: "+itemCardType);
				return playerDTO;
			}
			else{
				playerDTO.setMessageToSend("You cannot use this card at the moment or you don't own this card!");
				return playerDTO; 
			}
			}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			
		 return playerDTO; 
		}
		
	}

	@Override
	public PlayerDTO performSpotLightUse(String itemCardType, Integer yourId, Integer numberGame, String sector) throws RemoteException {
				if(gameManager.canAct(numberGame, yourId)){
					PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
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
				
				//RIMUOVERE LA CARTA DALL ITEM DECK DEL YOURCHARACTER
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" use the Item Card " + itemCardType ) ;
				playerDTO.setMessageToSend("You've used the Item Card" + itemCardType);
				return playerDTO;
			}
			else{
				playerDTO.setMessageToSend("You don't own this card or you cannot use it at the moment.");
				return playerDTO; 
			}
		}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			return playerDTO; 
		}
		
	}

	@Override
	public PlayerDTO performDiscardCard(String itemCardType, Integer yourId,
			Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
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
				playerDTO.setMessageToSend("You succesfully discard a card of type:"+itemCardType +"!");
				return playerDTO;
				
			}
			else{
				playerDTO.setMessageToSend("You can't discard this Card!");
				return playerDTO;
				
			}
		}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			return playerDTO;
		}
	}
	

	

}
