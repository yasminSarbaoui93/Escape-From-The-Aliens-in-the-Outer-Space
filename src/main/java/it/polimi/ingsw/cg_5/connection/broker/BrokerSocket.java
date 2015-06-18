package it.polimi.ingsw.cg_5.connection.broker;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BrokerSocket extends Thread{
	private final int portNumber = 7777;
	private boolean listening = true;
	private ArrayList<BrokerThread> subscribers = new ArrayList<BrokerThread>();
	private String topic;
	
	
	public BrokerSocket(String topic){
		this.topic = topic;
		this.start();
	}
	
	@Override
	public void run() {
		try(ServerSocket brokerSocket = new ServerSocket(portNumber)){
			while(listening){
				BrokerThread brokerThread = new BrokerThread(brokerSocket.accept());
				brokerThread.start();
				System.out.println("Adding new subscriber");
				subscribers.add(brokerThread);
			}
		}catch(IOException e){
			System.err.println("Cannot listen on port: "+portNumber);
			System.exit(-1);
		}
	}
	
	private void publish(String msg){
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message");
			for (BrokerThread sub : subscribers) {
				sub.dispatchMessage(msg);
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
	
	public static void main(String[] args) {		
		try {
			while (true) {
				String inputLine = stdin.nextLine();
				broker.publish(inputLine);
			}
		}catch(NoSuchElementException e) {}
		
	}
	

}
