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
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * It receives a string command by the client. The first string refers to the kind of action the client wants to execute, while the others
	 * are the parameters needed to be sent as input of the methods that actually make the controls and change the game state.
	 * This run keeps on analizing the input messages until the string command isn't equal to QUIT.
	 */
	@Override
	public void run(){
		String command;

		Scanner in;
		do{
			
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
					System.out.println("ID GIOCATORE LATO SERVER "+yourID);
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
			
			if(stringToRead.toUpperCase().equals("END_TURN")){
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				PlayerDTO playerDTO;
				
				try {
					playerDTO = gameManager.getGameRules().performEndTurn(yourId, numberGame);
					client.sendDTO(playerDTO);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(stringToRead.toUpperCase().equals("DRAW")){
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				PlayerDTO playerDTO;
				
				try {
					playerDTO = gameManager.getGameRules().performDrawCard(yourId, numberGame);
					client.sendDTO(playerDTO);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			if(stringToRead.toUpperCase().equals("USE_CARD")){
				String itemCardType = in.next();
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				PlayerDTO playerDTO;
				
				try {
					playerDTO = gameManager.getGameRules().performUseCard(itemCardType, yourId, numberGame);
					client.sendDTO(playerDTO);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(stringToRead.toUpperCase().equals("SPOT")){
				String itemCardType = in.next();
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				String sector = in.next();
				PlayerDTO playerDTO;
				
				try{
					playerDTO = gameManager.getGameRules().performSpotLightUse(itemCardType, yourId, numberGame, sector);
					client.sendDTO(playerDTO);
				} catch(RemoteException e1){
					e1.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
			if(stringToRead.toUpperCase().equals("BLUFF")){
				String bluffSector = in.next();
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				PlayerDTO playerDTO;
				try{
					playerDTO = gameManager.getGameRules().bluffSector(bluffSector, yourId, numberGame);
					client.sendDTO(playerDTO);
				} catch(RemoteException e1){
					e1.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
				
			}
			
			if(stringToRead.toUpperCase().equals("DISCARD")){
				String itemCardType = in.next();
				Integer yourId = Integer.parseInt(in.next());
				Integer numberGame = Integer.parseInt(in.next());
				PlayerDTO playerDTO;
				try{
					playerDTO = gameManager.getGameRules().performDiscardCard(itemCardType, yourId, numberGame);
					client.sendDTO(playerDTO);
				} catch(RemoteException e1){
					e1.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
			//questa sarà la risposta del server al client
			
		}while(!command.toUpperCase().equals("QUIT")); 
		//client.close();
		in.close();
	}

}
