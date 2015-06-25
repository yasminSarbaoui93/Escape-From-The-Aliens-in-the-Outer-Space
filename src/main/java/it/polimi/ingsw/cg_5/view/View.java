package it.polimi.ingsw.cg_5.view;


import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.view.subscriber.Subscriber;

import java.io.Serializable;



/**Class that interfaces with the user to get the commands
 * @author Yasmin
 *
 */
public class View implements Serializable{

	/**
	 * 
	 */
	private ViewController viewController;
	private static final long serialVersionUID = 1L;
	private Integer PlayerID=-1;
	private Client client;
	private Integer currentPlayerId=-2;

	
	private String name;
	private Subscriber subscriber;
	private int numberGame = -1;

	private Character character;
	//private MatchState matchState;
		


	/**Constructor of the view that has at attributes the name of the user, the client that will interface with the connection (rmi or socket) and the subscriber that will get the message thrown by the broker.
	 * @param name
	 * @param client
	 * @param subscriber
	 * @throws Exception
	 */
	public View (String name, Client client, Subscriber subscriber) throws Exception{
		super();
		this.name=name;
		this.client= client;
		this.subscriber = subscriber; 
	   // subscriber.setView(this);
		numberGame=0;
		/// prova stub per ogni view
		
	}
	public Integer getCurrentPlayerId() {
		return currentPlayerId;
	}
	public void setCurrentPlayerId(Integer currentPlayerId) {
		this.currentPlayerId = currentPlayerId;
	}
	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

	public ViewController getViewController() {
		return viewController;
	}
	public Integer getNumberGame(){
		return this.numberGame;
	}
	
	public void setPlayerID(Integer playerID) {
		PlayerID = playerID;
	}
	
	public String getName() {
		return name;
	}


	public void setNumberGame(Integer numberGame){
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
	
	
	public Integer getPlayerID() {
		return PlayerID;
	}
	public Subscriber getSubscriber() {
		return subscriber;
	}
	
	
/*	public static void main (String args []) throws Exception{
		
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
		
			
		View view= new View(nomeUtente, client,subscriber);
		view.getSubscriber().setView(view);
		
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

*/
	

	


}
