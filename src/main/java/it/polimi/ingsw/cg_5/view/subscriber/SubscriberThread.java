package it.polimi.ingsw.cg_5.view.subscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SubscriberThread extends Thread {
	private Socket subSocket;
	private BufferedReader in; //gets the message that arrive from the printwriter
	private final String address = "127.0.0.1";
	private String name;
	private final int port = 1039;
	
	public SubscriberThread (String name){
		this.name = name;
		try {
			subscribe();
			System.out.println("a");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Il thread rimane in ascolto dei msg che gli arrivan dal broker
	@Override
	public void run(){
		System.out.println("prova a fare receive number game");
		
		
		while(true){
			receive();
		//	Integer numberGame = receiveNumberGame();
			//System.out.println("number Game is: "+numberGame);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String receive(){
		String msg = null;
		try {
			if(in.ready()){
			msg = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(msg !=null){
			System.out.println("Thread "+name+" received the message: "+msg);
		}
		return msg;
	}
	
	public Integer receiveNumberGame(){
		Integer numberGame = null;
		try {
			numberGame = Integer.parseInt(in.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		if(numberGame != null){
			System.out.println("Thread "+name+" received the number of the game: "+numberGame);
		}
		return numberGame;
	}
	
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
