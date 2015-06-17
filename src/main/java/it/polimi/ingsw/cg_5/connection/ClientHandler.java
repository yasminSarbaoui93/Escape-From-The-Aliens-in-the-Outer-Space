package it.polimi.ingsw.cg_5.connection;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.cg_5.controller.GameManager;
import it.polimi.ingsw.cg_5.view.Communicator;
public class ClientHandler extends Thread {
	
	Communicator client;
	GameManager gameManager;
	//RulesOfTheGame rulesOfTheGame;
	RemoteMethodsImpl executeRequest;
	public ClientHandler(Communicator client, GameManager gameManager, RemoteMethodsImpl executeRequest){
		this.client= client;
		this.gameManager = gameManager;
		this.executeRequest = executeRequest;
	}
	
	@Override
	public void run(){
		String command;

	
		do{
			//command sent by the client.
			command = client.receive();
			Scanner in = new Scanner(command);
			if(in.next().toUpperCase().equals("SUBSCRIBEREQUEST")){
				Integer yourID;
				String choosenMap = in.next();
				Integer maxSize = Integer.parseInt(in.next());
				String name = in.next();
				try {
					yourID = executeRequest.SubscribeRequest(choosenMap, maxSize, name);
					client.send(yourID.toString());
					
				} catch (RemoteException e) {
				
					e.printStackTrace();
				} catch (NotBoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in.close();
				
			
			}
			
			//questa sarà la risposta del server al client
			
		}while(!command.toUpperCase().equals("QUIT"));
		//client.close();
	}

}
