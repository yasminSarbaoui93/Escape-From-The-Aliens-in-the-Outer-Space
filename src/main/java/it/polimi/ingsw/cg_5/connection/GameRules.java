package it.polimi.ingsw.cg_5.connection;

import java.rmi.RemoteException;

import it.polimi.ingsw.cg_5.controller.EscapeMove;
import it.polimi.ingsw.cg_5.controller.GameManager;
import it.polimi.ingsw.cg_5.controller.Move;
import it.polimi.ingsw.cg_5.model.EscapeHatchType;
import it.polimi.ingsw.cg_5.model.EscapeSector;
import it.polimi.ingsw.cg_5.model.Sector;


public class GameRules {

	GameManager gameManager;
	SubscriberInterface subscriber;
	
	public GameRules (GameManager gameManager){
		this.gameManager = gameManager;
	}
	

	public Integer SubscribeRequest (String choosenMap, int choosenMaxSize, String name) throws RemoteException {
		
		Integer yourId =gameManager.getPlayerListManager().addToChosenList(choosenMap, choosenMaxSize, subscriber);
		System.out.println("The player with ID:" + yourId + "joined the game");
		System.out.println("Matches started: " + gameManager.getListOfMatch());
		this.gameManager.MatchCreator();
		return yourId;
		
	}
	
	
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
}
