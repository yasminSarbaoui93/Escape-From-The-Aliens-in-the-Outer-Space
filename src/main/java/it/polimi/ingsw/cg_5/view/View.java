package it.polimi.ingsw.cg_5.view;


import it.polimi.ingsw.cg_5.connection.SubscriberInterface;

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
	private RmiClient rmiClient;
	private String name;
	private Subscriber subscriber;
	private int numberGame;
		


	public View (String name) throws Exception{
		super();
		this.name=name;
		this.rmiClient= new RmiClient();
		subscriber = new Subscriber(name);
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
	public RmiClient getRmiClient() {
		return rmiClient;
	}
	
	
	public int getPlayerID() {
		return PlayerID;
	}
	public Subscriber getSubscriber() {
		return subscriber;
	}
	
	
	public static void main (String args []) throws Exception{
		
		System.out.println("Inserisci un nome utente: ");
		Scanner in = new Scanner (System.in);
		String nomeUtente = in.nextLine();
		View view= new View(nomeUtente);
		view.getSubscriber().setView(view);
				
		System.out.println("Con che mappa vuoi giocare?");
		String stringa = in.nextLine();
		System.out.println("Con quanti giocatori vuoi giocare al massimo?(max 8)");
		Integer maxSize= Integer.parseInt(in.nextLine());
		Integer yourId=view.getRmiClient().matchRequest(stringa, maxSize, view.getName());
	

		System.out.println("Sei stato aggiunto ad una Waiting List, il tuo Id per questa sessione sarà :" + yourId  );	


		
			while(true){
				System.out.println("Proviamo a muoverci: dimmi un settore!");
				String sector=in.nextLine();
				// PER ORA PASSIAMO 0 PERCHE' TESTO COL PRIMO GIOCO CHE VIENE CREATO NELLA LIST OF MATCH
				// CI SERVE SUBSCRIBER PER NOTIFICARE A TUTTI I COINVOLTI IL GIOCO CREATO E L'ID DEL GIOCO!!
				// CHE DOVRANNO SALVARE IN APPOSITO ATTRIBUTO DENTRO LA VIEW
				System.out.println(view.getRmiClient().moveRequest(sector, yourId,view.getNumberGame()));
				System.out.println(view.getRmiClient().drawCardRequest(yourId, view.getNumberGame()));
				System.out.println(view.getRmiClient().endTurnRequest(yourId, view.getNumberGame()));
	
				
			}
			//in.close();
	}


	


}
