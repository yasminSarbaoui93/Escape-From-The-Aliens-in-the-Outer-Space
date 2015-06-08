package rmiconnection;

import java.util.Scanner;

import PubSub.Subscriber;



public class View  {
	int PlayerID=101;
	RmiClient rmiClient;
	Subscriber subscriber;
	
	
	public RmiClient getRmiClient() {
		return rmiClient;
	}

	public View () throws Exception{

	this.rmiClient= new RmiClient();
	
	}

	public int getPlayerID() {
		return PlayerID;
	}
	
	public static void main (String args []) throws Exception{
		View view= new View ();
		Scanner in = new Scanner (System.in);
		
		System.out.println("Con che mappa vuoi giocare?");
		String stringa = in.nextLine();
		System.out.println("Con quanti giocatori vuoi giocare al massimo?(max 8)");
		Integer maxSize= Integer.parseInt(in.nextLine());
		Integer yourId=view.getRmiClient().matchRequest(stringa, maxSize);
		System.out.println("Sei stato aggiunto ad una Waiting List, il tuo Id per questa sessione sarà:" + yourId  );	
		while(true){
		System.out.println("Proviamo a muoverci: dimmi un settore!");
		String sector=in.nextLine();
		// PER ORA PASSIAMO 0 PERCHE' TESTO COL PRIMO GIOCO CHE VIENE CREATO NELLA LIST OF MATCH
		// CI SERVE SUBSCRIBER PER NOTIFICARE A TUTTI I COINVOLTI IL GIOCO CREATO E L'ID DEL GIOCO!!
		// CHE DOVRANNO SALVARE IN APPOSITO ATTRIBUTO DENTRO LA VIEW
		System.out.println(view.getRmiClient().moveRequest(sector, yourId,0));
		System.out.println(view.getRmiClient().drawCardRequest(yourId, 0));
		System.out.println(view.getRmiClient().endTurnRequest(yourId, 0));
		}
		//in.close();
	}


	

}
