package it.polimi.ingsw.cg_5.connection;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashSet;

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
import it.polimi.ingsw.cg_5.model.Alien;
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
	public synchronized Integer SubscribeRequest (String choosenMap, int choosenMaxSize, String name, String connectionType) throws NotBoundException, UnknownHostException, IOException, RemoteException {
		if(connectionType.toUpperCase().equals("RMI")){
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
			subscriber = (SubscriberInterfaceRmi)registry.lookup(name);
		}
		
		else{
			
			
			subscriber =SocketServer.getInstance().getBrokerThread();
		}
		
		Integer yourId =gameManager.getPlayerListManager().addToChosenList(choosenMap, choosenMaxSize, subscriber, connectionType);
		System.out.println("The player with ID:" + yourId + "joined the game");
		System.out.println("Matches started: " + gameManager.getListOfMatch());
		this.gameManager.MatchCreator(connectionType);
		return yourId;
		
	}
	
	
	/**It makes all the checks necessary to let the character perform the move action. The player DTO is updated and then sent back.
	 * @param sectorName
	 * @param yourId
	 * @param numberGame
	 * @return the player's data transfer object with all the updated informations of the character (such as the current sector and the rispective server answer message).
	 * @throws Exception 
	 */
	public PlayerDTO performMove(String sectorName, Integer yourId ,Integer numberGame) {
		
		
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
							
							
							this.gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "Now is the turn of the Player"
									+ this.gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
							if(gameManager.getListOfMatch().get(numberGame).getMatchState()==MatchState.ENDED){
								HashSet<Character> removeAlien=gameManager.getListOfMatch().get(numberGame).getGameState().getWinners();
								for(Character character :removeAlien ){
									if(character.getClass()==Alien.class){
										gameManager.getListOfMatch().get(numberGame).getGameState().getWinners().remove(character);
										gameManager.getListOfMatch().get(numberGame).getGameState().getLosers().add(character);
									}
									
								}
								this.gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "The game is ended and"
										+ "will be removed from the list of the game! \n"+"The winner are"+this.gameManager.getListOfMatch().get(numberGame).getGameState().getWinners());
								playerDTO.setMessageToSend("Since you ran away, you won the match. CONGRATULATIONS!!! /n Game Over");
								this.gameManager.getListOfMatch().remove(numberGame);
								playerDTO= new PlayerDTO("You escape!");
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
	
	
	/**It checks if the character can actually perform the attack and, if so, it checks if in the attacked sector there's any other character.
	 * @param yourId
	 * @param numberGame
	 * @return playerDTO updated with the turn state as Has Attacked
	 * @throws Exception 
	 */
	public PlayerDTO performAttack(Integer yourId ,Integer numberGame) {
		
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			Attack attack = new Attack(gameManager.getListOfMatch().get(numberGame).getGameState(),gameManager.getListOfMatch().get(numberGame));
			if(attack.checkAction()){
				attack.execute();
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				if(!attack.getPlayerToKill().isEmpty()){
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "The players/s"+
				attack.getPlayerToKill() +" was/were killed by the player with ID- " + 
			  gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID() + "!");
				}
				if(!attack.getSafeCharacter().isEmpty()){
					gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "The player "+ 
				attack.getSafeCharacter().get(0).getPlayerID() + "was attacked but he's alive thanks to the Defence Card");
				}

			
				this.gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "The player "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()+" has attacked.");
				/// qua 
				if(gameManager.getListOfMatch().get(numberGame).getMatchState()==MatchState.ENDED){
					this.gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "The game is ended and"
							+ "will be removed from the list of the game! \n"+"The winner are"+this.gameManager.getListOfMatch().get(numberGame).getGameState().getWinners());
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
	
	/**Controls if the player can end his turn or if it still has to perform any action before. If the turn can end, the turn goes to the next character, updating the current character of the model.
	 * @param yourId
	 * @param numberGame
	 * @return Player DTO with the message updated.
	 * @throws Exception 
	 */
	public PlayerDTO performEndTurn(Integer yourId,Integer numberGame) {
		
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
					this.gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "The game is ended and"
							+ "will be removed from the list of the game! \n"+"The winner are"+this.gameManager.getListOfMatch().get(numberGame).getGameState().getWinners());
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
	
	/**Controls if the player has performed the move and, if it's its turn and if the current sector is Dangerous and not Safe, all conditions neessary to draw a Game Card.
	 * It even controls if the Item Icon on the card is true. If so, the player will have to draw an item card before ending the turn.
	 * @param yourId
	 * @param numberGame
	 * @return playerDTO updated with the messages and the cards owned.
	 * @throws Exception 
	 */
	public PlayerDTO performDrawCard(Integer yourId,Integer numberGame){
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
					gameManager.getListOfMatch().get(numberGame).getBroker().publish(false,
							"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
							+" make noise in the Sector: " + gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getCurrentSector());
				}
				if(drawCard.getDrawnCard().getGameCardType()==GameCardType.SILENCE ){
					gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "Silence...");
				
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
	
	/**This method is called if the player's drawn the game card "noise in any sector". If the player draws it, he has to bluff his position and he can chose the sector where he wants to pretend to be to confuse the other players.
	 * @param bluffSector
	 * @param yourId
	 * @param numberGame
	 * @return playerDTO with messages updated
	 * @throws Exception 
	 */
	public PlayerDTO bluffSector(String bluffSector, Integer yourId , Integer numberGame)  {
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			if(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState()==TurnState.BLUFFING){
				try {
					gameManager.getListOfMatch().get(numberGame).getBroker().publish(false,
							"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
							+" make noise in the Sector: " + gameManager.getListOfMatch().get(numberGame).getGameState().getMap().takeSector(bluffSector));
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
	
	/**Its called when a player decides to use an item card. It's the only action that any player can do in any time of the game, even if it's not his turn.
	 * @param itemCardType
	 * @param yourId
	 * @param numberGame
	 * @return playerDTO with message updated and the remaining item cards that the player owns
	 * @throws Exception 
	 */
	public PlayerDTO performUseCard(String itemCardType, Integer yourId, Integer numberGame)  {
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());

			ItemCardType cardType = getTypeFromString(itemCardType);
			UseItemCard itemCard = new UseItemCard(gameManager.getListOfMatch().get(numberGame).getGameState(),
						cardType);
			if(itemCard.checkAction()){
				itemCard.execute();
				playerDTO.setTurnState(gameManager.getListOfMatch().get(numberGame).getGameState().getTurn().getTurnState());
				playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());			
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(false,
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" use the Item Card " + itemCardType) ;
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

	/**Controls if the player owns the cart spotlight and it's his turn. If so, he uses the spotlight card and removes it from the deck of the owned cards.
	 * @param itemCardType
	 * @param yourId
	 * @param numberGame
	 * @param sector
	 * @return PlayerDTO with the messages and the player's deck updated.
	 * @throws Exception 
	 */
	public PlayerDTO performSpotLightUse(String itemCardType, Integer yourId, Integer numberGame, String sector){
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
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(false, "The Player with ID -" + character.getPlayerID()
						+ "is in the Sector: " + character.getCurrentSector());
				}
				}
				
				//RIMUOVERE LA CARTA DALL ITEM DECK DEL YOURCHARACTER
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(false,
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" use the Item Card " + itemCardType) ;
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

	/**Mainly used when a character owns more than 3 item cards. This method takes as input the card type that the player wants to discard, and removes it from the player's deck.
	 * @param itemCardType
	 * @param yourId
	 * @param numberGame
	 * @return
	 * @throws Exception 
	 */
	public PlayerDTO performDiscardCard(String itemCardType, Integer yourId,
			Integer numberGame) {
		if(gameManager.canAct(numberGame, yourId)){
			PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
			ItemCardType cardType = getTypeFromString(itemCardType);
			
			DiscardItemCard discartCard = new DiscardItemCard(gameManager.getListOfMatch().get(numberGame).getGameState(),cardType);
			if(discartCard.checkAction()){
				discartCard.execute();
				gameManager.getListOfMatch().get(numberGame).getBroker().publish(false,
						"The Player with ID- "+gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter().getPlayerID()
						+" discard an Item Card of Type " + itemCardType) ;
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
	
	public void performSendMessage(String message, Integer yourId, Integer numberGame) {
		gameManager.getListOfMatch().get(numberGame).getBroker().publish(true, message);
	}
	/**Associates the input string to a specific item card.
	 * @param cardTypeName
	 * @return the item card that has the same name of the string given as parameter.
	 */
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
