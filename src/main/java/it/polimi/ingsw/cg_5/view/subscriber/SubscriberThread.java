package it.polimi.ingsw.cg_5.view.subscriber;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import it.polimi.ingsw.cg_5.model.Character;

import javax.swing.text.BadLocationException;

public class SubscriberThread extends Thread {
	private Socket subSocket;
	private BufferedReader in; //gets the message that arrive from the printwriter
	private final String address = "127.0.0.1";
	private String name;
	private final int port = 1040; //porta in cui si mette in ascolto il socket
	private SubscriberSocket subscriber;
	private ObjectOutput outobj;
	private ObjectInput inObj;
	
	public SubscriberThread (String name, SubscriberSocket subscriber){
		this.name = name;
		this.subscriber = subscriber;
		try {
			subscribe();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.outobj = new ObjectOutputStream(subSocket.getOutputStream());
			outobj.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.inObj = new ObjectInputStream(subSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run(){
		while(true){
			receive();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String receive() { 
		
		String msg = null;
		Scanner inMsg;
		String messageToPrint = null;

		try {
			if(in.ready()){
				msg = in.readLine();
				//System.out.println(msg);
				inMsg = new Scanner(msg);
				if(msg !=null){
					String messageReceived= inMsg.next();
					
					if(messageReceived.toUpperCase().equals("FALSE")){
						messageToPrint = inMsg.nextLine();
						this.subscriber.getView().getViewController().getEscape().getLogPanel().updateLogMessage(messageToPrint+"\n",Color.RED);
						System.out.println("Thread "+name+" received the message: "+messageToPrint);

					}
					
					if(messageReceived.toUpperCase().equals("NUMBERGAME")){
						messageToPrint = inMsg.next();
						System.out.println("PROVA NUMERO GIOCO SUBSCRIBER THREAD "+Integer.parseInt(messageToPrint));
						Integer numberGame = Integer.parseInt(messageToPrint);
						subscriber.getView().setNumberGame(numberGame);
					}
					if(messageReceived.toUpperCase().equals("PLAYERID")){
						messageToPrint = inMsg.next();
						System.out.println("PROVA PLAYER ID SUB THREAD: " +messageToPrint);
						Integer playerId = Integer.parseInt(messageToPrint);
						subscriber.getView().setPlayerID(playerId);
						subscriber.getView().getViewController().getEscape().getDtoPanel().updateDtoPanelCurrentId(playerId);
				
					}
					
					if(messageReceived.toUpperCase().equals("TRUE")){		
						messageToPrint = inMsg.nextLine();
						this.subscriber.getView().getViewController().getEscape().getMessagePanel().updateChatMessage(messageToPrint);
						System.out.println("Thread "+name+" received the chat message: "+messageToPrint);
						inMsg.close();
						return messageToPrint;
					} 
					
				if(messageReceived.toUpperCase().equals("CHARACTER")){
						receiveCharacter();			
				}
								
			}
		}
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
			
			
		
		
		return messageToPrint;
	}
	
	private Character receiveCharacter(){
		Character character = null;
		Object object =null;
		try {
		object = inObj.readObject();
		character = (Character)object;
		subscriber.getView().setCharacter(character);
		subscriber.getView().getViewController().getEscape().getDtoPanel().updateDtoPanel(subscriber.getView().getCharacter());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return character;		
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
