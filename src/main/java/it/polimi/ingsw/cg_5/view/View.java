package it.polimi.ingsw.cg_5.view;


import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberInterface;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberRmi;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;



//Classe che si interfaccia con l'utente per ricevere il comando

public class View implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int PlayerID=101;
	private Client client;
	private String name;
	private SubscriberRmi subscriber;
	private int numberGame = 0;
	private Character character;
		


	public View (String name, Client client) throws Exception{
		super();
		this.name=name;
		this.client = client;
		subscriber = new SubscriberRmi(name);
		numberGame=0;
		/// prova stub per ogni view
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		SubscriberInterface stub = (SubscriberInterface)UnicastRemoteObject.exportObject(this.subscriber, 0);
		registry.rebind(this.name, stub);
	}
	
	
	public int getNumberGame(){
		return this.numberGame;
	}
	
	public String getName() {
		return name;
	}


	public void setNumberGame(int numberGame){
		this.numberGame = numberGame;
	}
	public Client getClient() {
		return this.client;
	}
	
	public void setCharacter(Character character){
		this.character=character;
	}
	
	public Character getCharacter(){
		return this.character;
	}
	
	
	public int getPlayerID() {
		return PlayerID;
	}
	public SubscriberRmi getSubscriber() {
		return subscriber;
	}
	
	
	public static void main (String args []) throws Exception{
		
		System.out.println("Inserisci un nome utente: ");
		Scanner in = new Scanner (System.in);
		String nomeUtente = in.nextLine();
		System.out.println("What type of connection would you like to use? RMI/SOCKET");
		String connection = in.nextLine();
		
		Client client = null;
		try{
			if(connection.toUpperCase().equals("SOCKET")){
				client = new SocketClient("127.0.0.1", 1337);
			}
			
			if(connection.toUpperCase().equals("RMI")){
				client = new RmiClient();
			}
		
		}catch(IOException e){
			System.out.println("Unhandled connection");
		}
		
			
		View view= new View(nomeUtente, client);
		//view.getSubscriber().setView(view);
		
		System.out.println("Con che mappa vuoi giocare?");
		String stringa = in.nextLine();
		System.out.println("Con quanti giocatori vuoi giocare al massimo?(max 8)");
		Integer maxSize= Integer.parseInt(in.nextLine());
		Integer yourId=view.getClient().matchRequest(stringa, maxSize, view.getName());
	

		//System.out.println("Sei stato aggiunto ad una Waiting List, il tuo Id per questa sessione sarà :" + yourId  );	


		
			while(!stringa.equals(("QUIT"))){
				System.out.println("COSA VUOI FARE?!");
				stringa=in.nextLine().toUpperCase();
				// PER ORA PASSIAMO 0 PERCHE' TESTO COL PRIMO GIOCO CHE VIENE CREATO NELLA LIST OF MATCH
				// CI SERVE SUBSCRIBER PER NOTIFICARE A TUTTI I COINVOLTI IL GIOCO CREATO E L'ID DEL GIOCO!!
				// CHE DOVRANNO SALVARE IN APPOSITO ATTRIBUTO DENTRO LA VIEW
				if(stringa.equals("MOVE")){
					System.out.println("DOVE VUOI MUOVERTI?");
					String sector = in.nextLine();
					System.out.println(view.getClient().moveRequest(sector, yourId,view.getNumberGame()));
				}
				if(stringa.equals("ATTACK")){
					System.out.println(view.getClient().attackRequest(yourId, view.getNumberGame()));
				}
				if(stringa.equals("DRAW")){
				System.out.println(view.getClient().drawCardRequest(yourId, view.getNumberGame()));
				}
				if(stringa.equals("ENDTURN")){
				System.out.println(view.getClient().endTurnRequest(yourId, view.getNumberGame()));
				}
				if(stringa.equals("USECARD")){
				System.out.println("Which Item Card would you like to use?");
					String itemCardType = in.nextLine();
					if(!itemCardType.equals("SPOTLIGHT")){
					System.out.println(view.getClient().useCardRequest(itemCardType, yourId, view.getNumberGame()));
					}
					else{
					System.out.println("Which sector would you like to Spot?");
					String sector = in.nextLine();
						System.out.println(view.getClient().useSpotLightRequest(itemCardType, yourId, view.getNumberGame(),sector));
					}
				}
				if(stringa.equals("BLUFF")){
					System.out.println("Where you want to Bluff?");
					String sector = in.nextLine();
					System.out.println(view.getClient().bluffRequest(sector,yourId, view.getNumberGame()));
					}
				if(stringa.equals("DISCARD")){
					System.out.println("Whic card do you want to discard?");
					String itemCardType = in.nextLine();
					System.out.println(view.getClient().discardRequest(itemCardType,yourId, view.getNumberGame()));
					}

				
			}
			in.close(); 
	}


	


}
