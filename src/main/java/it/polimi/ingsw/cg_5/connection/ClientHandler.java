package it.polimi.ingsw.cg_5.connection;

import java.io.IOException;
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
	 * After calling the right methods, it sends back the Player's Data Object Transfer as response Server-Client.
	 */
	@Override
	public void run(){
		String command;

		Scanner in;
		do{
			
			command = client.receive();
			in = new Scanner(command);
			if(in.hasNext()){
				String stringToRead = in.next();
			
				PlayerDTO playerDTO;
				if(stringToRead.toUpperCase().equals("SUBSCRIBEREQUEST")){
					String choosenMap = in.next();
					Integer maxSize = Integer.parseInt(in.next());
					String name = in.next();
					String connectionType = in.next();
					try {
						Integer yourID = gameManager.getGameRules().SubscribeRequest(choosenMap, maxSize, name, connectionType);
						client.send(yourID.toString());
					
					}catch (IOException e) {
						System.err.println(e.getMessage());
					}
				}
			
				else{
					Integer yourId = Integer.parseInt(in.next());
					Integer numberGame = Integer.parseInt(in.next());			
					if(stringToRead.toUpperCase().equals("MOVE")){
						String sectorName = in.next();
						playerDTO = gameManager.getGameRules().performMove(sectorName, yourId, numberGame);	
							try {
								client.sendDTO(playerDTO);
							} catch (IOException e) {
								e.getMessage();
							}
									
					}
			
					if(stringToRead.toUpperCase().equals("ATTACK")){						
						playerDTO = gameManager.getGameRules().performAttack(yourId, numberGame);
						try {
							client.sendDTO(playerDTO);
						} catch (IOException e) {
							e.getMessage();
						}
					}
			
					if(stringToRead.toUpperCase().equals("END_TURN")){
			
						try {
							playerDTO = gameManager.getGameRules().performEndTurn(yourId, numberGame);
							client.sendDTO(playerDTO);
						
						} catch (IOException e) {
							e.getMessage();
						}
					}
			
					if(stringToRead.toUpperCase().equals("DRAW")){
		
						try {
							playerDTO = gameManager.getGameRules().performDrawCard(yourId, numberGame);
							client.sendDTO(playerDTO);
						} catch (IOException e) {
							e.getMessage();
						}
				
					}
			
					if(stringToRead.toUpperCase().equals("USE_CARD")){
			
						String itemCardType = in.next();
						try {
							playerDTO = gameManager.getGameRules().performUseCard(itemCardType, yourId, numberGame);
							client.sendDTO(playerDTO);
						}catch (IOException e) {
							e.getMessage();
						}
					}
			
					if(stringToRead.toUpperCase().equals("SPOT")){
						String sector = in.next();
						String itemCardType = in.next();
						try{
							playerDTO = gameManager.getGameRules().performSpotLightUse(itemCardType, yourId, numberGame, sector);
							client.sendDTO(playerDTO);
						}catch(IOException e){
							e.getMessage();
						}
					}
			
					if(stringToRead.toUpperCase().equals("BLUFF")){
						String bluffSector = in.next();
						try{
							playerDTO = gameManager.getGameRules().bluffSector(bluffSector, yourId, numberGame);
							client.sendDTO(playerDTO);
						}catch(IOException e){
							e.getMessage();
						}
				
					}
			
					if(stringToRead.toUpperCase().equals("DISCARD")){
						String itemCardType = in.next();
						try{
							playerDTO = gameManager.getGameRules().performDiscardCard(itemCardType, yourId, numberGame);
							client.sendDTO(playerDTO);
						} catch(IOException e){
							e.getMessage();
						}
					}
			
					if(stringToRead.toUpperCase().equals("CHAT")){
						String message = in.nextLine();
							gameManager.getListOfMatch().get(numberGame).getBroker().publish(true, message);
					
					}
				}
			}
		}while(!command.toUpperCase().equals("QUIT")); 
		client.close();
		in.close();
	}

}
