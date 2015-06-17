package it.polimi.ingsw.cg_5.connection;

import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.cg_5.controller.GameManager;
import it.polimi.ingsw.cg_5.view.Communicator;
public class ClientHandler extends Thread {
	
	Communicator client;
	GameManager gameManager;
	//RulesOfTheGame rulesOfTheGame;
	GameRules gameRules;
	public ClientHandler(Communicator client, GameManager gameManager, GameRules gameRules){
		this.client= client;
		this.gameManager = gameManager;
		this.gameRules = gameRules;
	}
	
	@Override
	public void run(){
		String command;

	
		do{
			//command sent by the client.
			command = client.receive();
			Scanner in = new Scanner(command);
			String stringToRead = in.next();
			if(stringToRead.toUpperCase().equals("SUBSCRIBEREQUEST")){
				Integer yourID;
				String choosenMap = in.next();
				Integer maxSize = Integer.parseInt(in.next());
				String name = in.next();
				try {
					yourID = gameRules.SubscribeRequest(choosenMap, maxSize, name);
					client.send(yourID.toString());
					
				} catch (RemoteException e) {
				
					e.printStackTrace();
				}
			}
			
			if(stringToRead.toUpperCase().equals("MOVE")){
				
				String sectorName = in.next();
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				PlayerDTO playerDTO = new PlayerDTO(gameManager.getListOfMatch().get(numberGame).getGameState().getCurrentCharacter());
				
				try {
					playerDTO = gameRules.performMove(sectorName, yourId, numberGame);
					client.sendDTO(playerDTO);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			in.close();
			//questa sarà la risposta del server al client
			
		}while(!command.toUpperCase().equals("QUIT"));
		//client.close();
	}

}
