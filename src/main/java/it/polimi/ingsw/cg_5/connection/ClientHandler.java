package it.polimi.ingsw.cg_5.connection;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.cg_5.controller.GameManager;
import it.polimi.ingsw.cg_5.view.Communicator;
public class ClientHandler extends Thread {
	
	Communicator client;
	GameManager gameManager;

	public ClientHandler(Communicator client){
		this.client= client;
		this.gameManager = GameManager.getInstance();
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
				String connectionType = in.next();
				try {
					Integer yourID = gameManager.getGameRules().SubscribeRequest(choosenMap, maxSize, name, connectionType);
					client.send(yourID.toString());
					
				} catch (RemoteException e) {
				
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
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
					playerDTO = gameManager.getGameRules().performMove(sectorName, yourId, numberGame);
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
					playerDTO = gameManager.getGameRules().performAttack(yourId, numberGame);
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
