package it.polimi.ingsw.cg_5.view.subscriber;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.text.BadLocationException;

public class SubscriberThread extends Thread {
	private Socket subSocket;
	private BufferedReader in; //gets the message that arrive from the printwriter
	private final String address = "127.0.0.1";
	private String name;
	private final int port = 1040; //porta in cui si mette in ascolto il socket
	private SubscriberSocket subscriber;
	
	public SubscriberThread (String name, SubscriberSocket subscriber){
		this.name = name;
		this.subscriber = subscriber;
		try {
			subscribe();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Il thread rimane in ascolto dei msg che gli arrivan dal broker
	@Override
	public void run(){
		//System.out.println("prova a fare receive number game");
		//System.out.println("Aggiunto al gioco numero: "+receiveNumberGame());
		//anche se entra in questo ciclo while non riceve i messaggi della dispatch
	//	receiveNumberGame();
	
		/*while(!isGameStarted){
			try {
				receiveNumberGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		
		while(true){
			
			try {
				receive();
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		//	Integer numberGame = receiveNumberGame();
			//System.out.println("number Game is: "+numberGame);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String receive() throws BadLocationException, IOException{ //QUI IL SUBSCRIBER NON RICEVE IL MESSAGGIO
		String msg = null;
		Scanner inMsg;
		String messageToPrint = null;
		if(in.ready()){
			msg = in.readLine();
			System.out.println(msg);
			
			inMsg = new Scanner(msg);
		
			System.out.println(msg);
			if(msg !=null){
			
				String messageReceived= inMsg.next();
				
				//messageReceived = inMsg.next();
				if(messageReceived.toUpperCase().equals("FALSE")){
					messageToPrint = inMsg.nextLine();
					this.subscriber.getView().getViewController().getEscape().getLogPanel().updateLogMessage(messageToPrint+"\n",Color.RED);
					System.out.println("Thread "+name+" received the message: "+messageToPrint);
					inMsg.close();
					return messageToPrint;
				}
				
				if(messageReceived.toUpperCase().equals("NUMBERGAME")){
					messageToPrint = inMsg.next();
					System.out.println("PROVA NUMERO GIOCO SUBSCRIBER THREAD "+Integer.parseInt(messageToPrint));
					Integer numberGame = Integer.parseInt(messageToPrint);
					subscriber.getView().setNumberGame(numberGame);
					inMsg.close();
					return messageToPrint;
				}
				if(messageReceived.toUpperCase().equals("PLAYERID")){
					messageToPrint = inMsg.next();
					System.out.println("PROVA PLAYER ID SUB THREAD: " +messageToPrint);
					Integer playerId = Integer.parseInt(messageToPrint);
					subscriber.getView().setPlayerID(playerId);
					subscriber.getView().getViewController().getEscape().getDtoPanel().updateDtoPanelCurrentId(playerId);
					inMsg.close();
					return messageToPrint;
				}
				
				else{//if(messageReceived.toUpperCase().equals("TRUE")){		
					System.out.println("CAPIRE PERCHE FA NO SUCH ELEMENT");
					messageToPrint = inMsg.nextLine();
					this.subscriber.getView().getViewController().getEscape().getMessagePanel().updateChatMessage(messageToPrint);
					System.out.println("Thread "+name+" received the chat message: "+messageToPrint);
					inMsg.close();
					return messageToPrint;
				}
							
			}
			
			inMsg.close();
		}
		return messageToPrint;
	}
	
/*	public Integer receiveNumberGame() throws IOException{
		//BufferedReader inNum = new BufferedReader(new InputStreamReader(subSocket.getInputStream()));
		String ciao = in.readLine();
		Scanner inNumero = new Scanner(ciao);
		System.out.println(ciao+" ciaociaociao");
		
		Integer numberGame = null;
		try {
			//if(in.ready()){
				numberGame = Integer.parseInt(inNumero.next());
				System.out.println("PROVA NUMERO GIOCO"+numberGame);
				subscriber.getView().setNumberGame(numberGame);
				isGameStarted = true;
			//}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		//}
		
		//if(numberGame != null){
			//System.out.println("Thread "+name+" received the number of the game: "+numberGame);
			
			
		}
		return numberGame;
	}*/
	
	//Subscription to the one and only topic !
	private void subscribe() throws UnknownHostException, IOException{
		subSocket = new Socket(address, port); //Crea il socket verso il subscriber a cui è associato
		in = new BufferedReader(new InputStreamReader(subSocket.getInputStream()));
		
	}
	
	public void close(){
		try {
			subSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		in=null;
		subSocket = null;
		System.gc();
	}
	
}
