package it.polimi.ingsw.cg_5.connection;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.cg_5.controller.GameManager;
import it.polimi.ingsw.cg_5.view.Communicator;
import it.polimi.ingsw.cg_5.view.User;
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

		Scanner in;
		do{
			//command sent by the client.
			command = client.receive();
			System.out.println(command);
			in = new Scanner(command);
			String stringToRead = in.next();
			if(stringToRead.toUpperCase().equals("SUBSCRIBEREQUEST")){
				String choosenMap = in.next();
				Integer maxSize = Integer.parseInt(in.next());
				String name = in.next();
				try {
					Integer yourID = gameRules.SubscribeRequest(choosenMap, maxSize, name);
					client.send(yourID.toString());
					
				} catch (RemoteException e) {
				
					e.printStackTrace();
				}
			}
			
			if(stringToRead.toUpperCase().equals("MOVE")){
				
				String sectorName = in.next();
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				PlayerDTO playerDTO;
				System.out.println(sectorName+yourId+numberGame);
				
				try {
					playerDTO = gameRules.performMove(sectorName, yourId, numberGame);
					client.sendDTO(playerDTO);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
			if(stringToRead.toUpperCase().equals("ATTACK")){
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				PlayerDTO playerDTO;
				
				try {
					playerDTO = gameRules.performAttack(yourId, numberGame);
					client.sendDTO(playerDTO);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			//questa sarà la risposta del server al client
			
		}while(!command.toUpperCase().equals("QUIT"));
		//client.close();
		in.close();
	}

}
