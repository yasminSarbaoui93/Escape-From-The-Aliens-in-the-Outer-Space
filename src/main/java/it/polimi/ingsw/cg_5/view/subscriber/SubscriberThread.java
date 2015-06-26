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
	private BufferedReader in; 
	private final String address = "127.0.0.1";
	private String name;
	private final int port = 1040;
	private SubscriberSocket subscriber;
	private ObjectOutput outobj;
	private ObjectInput inObj;
	
	public SubscriberThread (String name, SubscriberSocket subscriber){
		this.name = name;
		this.subscriber = subscriber;
		try {
			subscribe();
			
		} catch (IOException e) {
			System.err.println("Cannot connect..Trobles creating a new socket!");
		}
		try {
			this.outobj = new ObjectOutputStream(subSocket.getOutputStream());
			outobj.flush();
			this.inObj = new ObjectInputStream(subSocket.getInputStream());
		} catch (IOException e) {
			System.err.println("Cannot create a new object input/output stream!"+e.getMessage());
		}


	}

	@Override
	public void run(){
		while(true){
			receive();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				System.err.println("Tread woke up or couldn't sleep");
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
				inMsg = new Scanner(msg);
				if(msg !=null){
					String messageReceived= inMsg.next();
					
					if(messageReceived.toUpperCase().equals("FALSE")){
						messageToPrint = inMsg.nextLine();
						this.subscriber.getView().getViewController().getEscape().getLogPanel().updateLogMessage(messageToPrint,Color.WHITE);
						System.out.println("Thread "+name+" received the message: "+messageToPrint);

					}
					
					if(messageReceived.toUpperCase().equals("NUMBERGAME")){
						messageToPrint = inMsg.next();
						Integer numberGame = Integer.parseInt(messageToPrint);
						subscriber.getView().setNumberGame(numberGame);
					}
					if(messageReceived.toUpperCase().equals("PLAYERID")){
						messageToPrint = inMsg.next();
						Integer playerId = Integer.parseInt(messageToPrint);
						subscriber.getView().setCurrentPlayerId(playerId);
						subscriber.getView().getViewController().getEscape().getDtoPanel().updateDtoPanelCurrentId(playerId);
				
					}
					
					if(messageReceived.toUpperCase().equals("TRUE")){		
						messageToPrint = inMsg.nextLine();
						this.subscriber.getView().getViewController().getEscape().getMessagePanel().updateChatMessage(messageToPrint+"\n");
						System.out.println("Thread "+name+" received the chat message: "+messageToPrint);

					} 
					
				if(messageReceived.toUpperCase().equals("CHARACTER")){
					try {
						synchronized(inObj){
						inObj.wait(10);}
					} catch (InterruptedException e) {
						System.err.println("Couldn't make the object input stream wait 10ms.");
					}
						receiveCharacter();			
				}
								
			}
				inMsg.close();	
		}
			

		}  catch (IOException e) {
			System.err.println("I/O trouble..impossible to receive the message!");
		} catch (BadLocationException e) {
			System.err.println(e.getMessage());
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
			System.err.println("The character didn't arrive to the object input stream");
		} catch (ClassNotFoundException e) {
			System.err.println("Cannot cast onto Character class");
		}
		return character;		
	}
	
	private void subscribe() throws UnknownHostException, IOException{
		subSocket = new Socket(address, port);
		in = new BufferedReader(new InputStreamReader(subSocket.getInputStream()));
		
	}
	
	public void close(){
		try {
			subSocket.close();
		} catch (IOException e) {
			System.err.println("Impossible to close the socket "+e.getMessage());
		}
		in=null;
		subSocket = null;
		System.gc();
	}
	
}
