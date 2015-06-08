package rmiconnection;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import PubSub.Subscriber;

public class RmiClient {
	private static final String NAME = "room";
	private final static String HOST="127.0.0.1"; 
	private final static int PORT=1099;

	
	public RmiClient () throws Exception{
		Scanner in = new Scanner (System.in);
		try{
			Registry registry = LocateRegistry.getRegistry(HOST,PORT);
			RemoteMethods remoteMethods1 = (RemoteMethods) registry.lookup(NAME); 
			
			System.out.println("Con che mappa vuoi giocare?");
			String stringa = in.nextLine();
			System.out.println("Con quanti giocatori vuoi giocare al massimo?(max 8)");
			Integer maxSize= Integer.parseInt(in.nextLine());
			Integer yourId=remoteMethods1.SubscribeRequest(stringa, maxSize);
			System.out.println("Sei stato aggiunto ad una Waiting List, il tuo Id per questa sessione sarà:" + yourId  );	
			System.out.println("Proviamo a muoverci: dimmi un settore!");
			String sector=in.nextLine();
			// PER ORA PASSIAMO 0 PERCHE' TESTO COL PRIMO GIOCO CHE VIENE CREATO NELLA LIST OF MATCH
			// CI SERVE SUBSCRIBER PER NOTIFICARE A TUTTI I COINVOLTI IL GIOCO CREATO E L'ID DEL GIOCO!!
			// CHE DOVRANNO SALVARE IN APPOSITO ATTRIBUTO DENTRO LA VIEW
			System.out.println(remoteMethods1.performMove(sector, yourId,0));
			System.out.println(remoteMethods1.performAttack(yourId,0));
			
			in.close();
		}
		catch( Exception e){
		e.printStackTrace();
		//System.err.println("Connessione Fallita!");
		}
		
	
		
	
	}

}