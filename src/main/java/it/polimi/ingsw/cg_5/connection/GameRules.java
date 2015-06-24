package it.polimi.ingsw.cg_5.connection;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.cg_5.connection.broker.PubSubCommunication;
import it.polimi.ingsw.cg_5.controller.Attack;
import it.polimi.ingsw.cg_5.controller.DiscardItemCard;
import it.polimi.ingsw.cg_5.controller.DrawCardFromGamedeck;
import it.polimi.ingsw.cg_5.controller.EndTurn;
import it.polimi.ingsw.cg_5.controller.EscapeMove;
import it.polimi.ingsw.cg_5.controller.GameManager;
import it.polimi.ingsw.cg_5.controller.MatchState;
import it.polimi.ingsw.cg_5.controller.Move;
import it.polimi.ingsw.cg_5.controller.UseItemCard;
import it.polimi.ingsw.cg_5.controller.UseSpotLight;
import it.polimi.ingsw.cg_5.model.Card;
import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.model.EscapeHatchType;
import it.polimi.ingsw.cg_5.model.EscapeSector;
import it.polimi.ingsw.cg_5.model.GameCardType;
import it.polimi.ingsw.cg_5.model.ItemCardType;
import it.polimi.ingsw.cg_5.model.Sector;
import it.polimi.ingsw.cg_5.model.TurnState;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberInterfaceRmi;
public class GameRules {

	GameManager gameManager;
	PubSubCommunication subscriber;

	public GameRules (GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	
	/**This method sends a request to the Specific server for joining a new game. If the game can start, it creates a new one;
	 * else, it adds the player to a waiting list, giving back the univoque ID of the player.
	 * @param choosenMap
	 * @param choosenMaxSize
	 * @param name
	 * @return playerId, univoque id of the player.
	 * @throws NotBoundException 
	 * @throws IOException, RemoteException 
	 * @throws UnknownHostException 
	 */
	public Integer SubscribeRequest (String choosenMap, int choosenMaxSize, String name, String connectionType) throws NotBoundException, UnknownHostException, IOException, RemoteException {
		if(connectionType.toUpperCase().equals("RMI")){
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
			subscriber = (SubscriberInterfaceRmi)registry.lookup(name);
		}
		
		else{
			
			
			subscriber =SocketServer.getInstance().getBrokerThread();
			//devo fare la get thel brokerThread creato nel server !!! e passarlo in ingresso al metodo
			
		}
		//ULTIMO PROBLEMA DA RISOLVERE: IL SUBSCRIBER SOCKET DEVE ESSERE PASSATO IN QUALCHE MODO ! PER IL RESTO ORA FUNZIONA
		//subscriber = new SubscriberSocket(name);
		Integer yourId =gameManager.getPlayerListManager().addToChosenList(choosenMap, choosenMaxSize, subscriber);
		System.out.println("The player with ID:" + yourId + "joined the game");
		System.out.println("Matches started: " + gameManager.getListOfMatch());
		this.gameManager.MatchCreator(connectionType);
		return yourId;
		
	}
	
	
	/**
	 * @param sectorName
	 * @param yourId
	 * @param numberGame
	 * @return
	 * @throws RemoteException
	 */
	public PlayerDTO performMove(String sectorName, Integer yourId ,Integer numberGame) throws RemoteException {
		
		
		try{
			
			if(gameManager.canAct(numberGame, yourId)){
				
				PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
				Sector destinationSector=gameManager.getListOfMatch().get(numberGame).getGameState().getMap().takeSector(sectorName);
				if(destinationSector.getClass() != EscapeSector.class){
					Move move = new Move(gameManager.getListOfMatch().get(numberGame).getGameState(),destinationSector); 
					if(move.checkAction()){
						move.execute();
						playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
						playerDTO.setYourCharacter(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
						playerDTO.setMessageToSend("You moved onto the sector "+ destinationSector);
						return playerDTO;
					}else {
						playerDTO.setMessageToSend("You cannot move onto that sector.");
						playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
						return playerDTO;
					}
				}
				else {
					EscapeMove runAway = new EscapeMove(gameManager.getListOfMatch().get(numberGame).getGameState(), destinationSector,gameManager.getListOfMatch().get(numberGame));
					if(runAway.checkAction()){
						runAway.execute();
						playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
						playerDTO.getYourCharacter().setCurrentSector(destinationSector);
						if(runAway.getEscapeCard().getEscapeHatchType()==EscapeHatchType.GREEN_SHALLOP){
							
							this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("Now is the turn of the Player"
									+ this.gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter(),false);
							if(gameManager.getListOfMatch().get(numberGame).getMatchState()==MatchState.ENDED){
								this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("The game is ended and"
										+ "will be removed from the list of the game! \n"+"The winner are"+this.gameManager.getListOfMatch().get(numberGame).getGameState().getWinners() , false);
								playerDTO.setMessageToSend("Since you ran away, you won the match. CONGRATULATIONS!!! /n Game Over");
								this.gameManager.getListOfMatch().remove(numberGame);
								return playerDTO;
								}
							playerDTO.setMessageToSend("Since you ran away, you won the match. CONGRATULATIONS!!!");
							
							return playerDTO;
						}
						else{
							playerDTO.setMessageToSend("You destroyed the shallop. Look for other escape hatch.");
							playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
							return playerDTO;
						}
						
					}else{
						playerDTO.setMessageToSend("You cannot move onto that sector.");
						playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
						return playerDTO;
					}
				
				}
				
			}
			else{			
				PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game ore it's not your turn");	
				return playerDTO;				
			}
				
		}catch(NullPointerException e){
			e.printStackTrace();
			PlayerDTO playerDTO= new PlayerDTO("Impossibile to find your character");
			return playerDTO;
		}
	}
	
	
	public PlayerDTO performAttack(Integer yourId ,Integer numberGame) throws RemoteException {
		
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			Attack attack = new Attack(gameManager.getListOfMatch().get(numberGame).getGameState(),gameManager.getListOfMatch().get(numberGame));
			if(attack.checkAction()){
				attack.execute();
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				if(!attack.getPlayerToKill().isEmpty()){
				gameManager.getListOfMatch().get(numberGame).getBroker().publish("The players/s"+
				attack.getPlayerToKill() +" was/were killed by the player with ID- " + 
			  gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID() + "!",false);
				}
				if(!attack.getSafeCharacter().isEmpty()){
					gameManager.getListOfMatch().get(numberGame).getBroker().publish("The player "+ 
				attack.getSafeCharacter().get(0).getPlayerID() + "was attacked but he's alive thanks to the Defence Card",false);
				}
			
				this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("The player "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()+" has attacked.",false);
				/// qua 
				if(gameManager.getListOfMatch().get(numberGame).getMatchState()==MatchState.ENDED){
					this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("The game is ended and"
							+ "will be removed from the list of the game! \n"+"The winner are"+this.gameManager.getListOfMatch().get(numberGame).getGameState().getWinners() , false);
					playerDTO.setMessageToSend("Game Over");
					this.gameManager.getListOfMatch().remove(numberGame);
					return playerDTO;
					}
				playerDTO.setMessageToSend("You've attacked!!");
				return playerDTO;
			}
			else{
				playerDTO.setMessageToSend("You cannot attack! Maybe you are a Human or you haven't drawn yet");
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
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
			EndTurn endTurn = new EndTurn(gameManager.getListOfMatch().get(numberGame).getGameState(),gameManager.getListOfMatch().get(numberGame));
			if(endTurn.checkAction()){		
				endTurn.execute();
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				playerDTO.setMessageToSend("Your turn's over!");
				
				// qua
				if(gameManager.getListOfMatch().get(numberGame).getMatchState()!=(MatchState.ENDED)){
					gameManager.getListOfMatch().get(numberGame).getBroker().publishNumberGame(numberGame, gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID());
					playerDTO.setCurrentCharacter(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID());
					return playerDTO;
					}
				
				if(gameManager.getListOfMatch().get(numberGame).getMatchState()==MatchState.ENDED){
					this.gameManager.getListOfMatch().get(numberGame).getBroker().publish("The game is ended and"
							+ "will be removed from the list of the game! \n"+"The winner are"+this.gameManager.getListOfMatch().get(numberGame).getGameState().getWinners() , false);
					playerDTO.setMessageToSend("Game Over");
					this.gameManager.getListOfMatch().remove(numberGame);
					return playerDTO;
					}
				

			}
			else 
				playerDTO.setMessageToSend("You can't end your turn yet! Check if you have to do something else or if your item deck has more than 3 cards!");
			playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());	
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
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				if(drawCard.getDrawnCard().isItemIcon()==true ){
					if(drawCard.checkItemDecks()){
						Card itemCard= gameManager.getListOfMatch().get(numberGame).getGameState().currentCharacterDrawsItemCard();

						message = "The ItemIcon was true and you draw the Item Card: " + itemCard +"\n";
					}
					else {
						message = "The ItemIcon was true but the ItemDeck has no more cards! \n";
						}
					}
				if(drawCard.getDrawnCard().getGameCardType()==GameCardType.NOISE_YOUR_SECTOR){
					gameManager.getListOfMatch().get(numberGame).getBroker().publish(
							"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
							+" make noise in the Sector: " + gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getCurrentSector(),false);
				}
				if(drawCard.getDrawnCard().getGameCardType()==GameCardType.SILENCE ){
					gameManager.getListOfMatch().get(numberGame).getBroker().publish("Silence...",false);
				
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
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				return playerDTO;
			}
		}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			return playerDTO;
		}
		
	}
	
	public PlayerDTO bluffSector(String bluffSector, Integer yourId , Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			if(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState()==TurnState.BLUFFING){
				try {
					gameManager.getListOfMatch().get(numberGame).getBroker().publish(
							"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
							+" make noise in the Sector: " + gameManager.getListOfMatch().get(numberGame).getGameState().getMap().takeSector(bluffSector),false);
					gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
					playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
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
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				return playerDTO;
			}
		}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			return playerDTO; 
		}
	}
	
	public PlayerDTO performUseCard(String itemCardType, Integer yourId, Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());

			ItemCardType cardType = getTypeFromString(itemCardType);
			UseItemCard itemCard = new UseItemCard(gameManager.getListOfMatch().get(numberGame).getGameState(),
						cardType);
			if(itemCard.checkAction()){
				itemCard.execute();
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());			
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" use the Item Card " + itemCardType,false ) ;
				playerDTO.setMessageToSend("You've used the item card: "+itemCardType);
				return playerDTO;
			}
			else{
				playerDTO.setMessageToSend("You cannot use this card at the moment or you don't own this card!");
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				return playerDTO; 
			}
			}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			
		 return playerDTO; 
		}
		
	}

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
						+ "is in the Sector: " + character.getCurrentSector(),false);
				}
				}
				
				//RIMUOVERE LA CARTA DALL ITEM DECK DEL YOURCHARACTER
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" use the Item Card " + itemCardType,false ) ;
				playerDTO.setMessageToSend("You've used the Item Card" + itemCardType);
				return playerDTO;
			}
			else{
				playerDTO.setMessageToSend("You don't own this card or you cannot use it at the moment.");
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				return playerDTO; 
			}
		}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			return playerDTO; 
		}
		
	}

	public PlayerDTO performDiscardCard(String itemCardType, Integer yourId,
			Integer numberGame) throws RemoteException {
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			ItemCardType cardType = getTypeFromString(itemCardType);
			
			DiscardItemCard discartCard = new DiscardItemCard(gameManager.getListOfMatch().get(numberGame).getGameState(),cardType);
			if(discartCard.checkAction()){
				discartCard.execute();
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" discard an Item Card of Type " + itemCardType ,false) ;
				playerDTO.setMessageToSend("You succesfully discard a card of type:"+itemCardType +"!");
				return playerDTO;
				
			}
			else{
				playerDTO.setMessageToSend("You can't discard this Card!");
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				return playerDTO;
				
			}
		}
		else{
			PlayerDTO playerDTO = new PlayerDTO("You don't belong to any game or it's not your turn!");
			return playerDTO;
		}
	}
	public ItemCardType getTypeFromString(String cardTypeName){
		ItemCardType cardtypeback =null;
		if(cardTypeName.equals("ATTACK"))
			cardtypeback=ItemCardType.ATTACK;
		if(cardTypeName.equals("SEDATIVES"))
			cardtypeback=ItemCardType.SEDATIVES;
		if(cardTypeName.equals("TELEPORT"))
			cardtypeback=ItemCardType.TELEPORT;
		if(cardTypeName.equals("ADRENALINE"))
			cardtypeback=ItemCardType.ADRENALINE;
		if(cardTypeName.equals("DEFENCE"))
			cardtypeback=ItemCardType.DEFENCE;
		if(cardTypeName.equals("SPOTLIGHT"))
			cardtypeback=ItemCardType.SPOTLIGHT;
		return cardtypeback;
	}



}
