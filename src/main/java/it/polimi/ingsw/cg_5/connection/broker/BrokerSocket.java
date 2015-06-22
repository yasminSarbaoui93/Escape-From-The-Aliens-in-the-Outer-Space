package it.polimi.ingsw.cg_5.connection.broker;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
public class BrokerSocket /*extends Thread */implements Broker{
	
	private final int portNumber = 3333;
	private boolean listening = true;
	private ArrayList<BrokerThread> subscribers = new ArrayList<BrokerThread>();
	private String topic;
	
	
	public BrokerSocket(String topic){
		this.topic = topic;
		//this.start();
	}
	
	@Override
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	/*@Override
	public void run() {
		try(ServerSocket brokerSocket = new ServerSocket(portNumber)){ //QUESTO PEZZO DA SPOSTARE NEL BROKER THREAD ! CREA UN THREAD CHE SI METTE IN ASCOLTO SULLA PORTA E ACCETTA CONNESSIONI MA L'ERRORE CHE FACCIO è CHE NON ASSOCIO I BTHREAD AI RISPETTIVI SUBTHREAD E NON ASSOCIO IL BTHREAD AL BROKER SOCKET
			while(listening){
				
				brokerSocket.accept();// rimane in attesa che un client esterno si sottoscriva 
			//	BrokerThread brokerThread = new BrokerThread(brokerSocket.accept());
			//	brokerThread.start();
			//	System.out.println("Adding new subscriber");
			}
		}catch(IOException e){
			System.err.println("Cannot listen on port: "+portNumber);
			System.exit(-1);
		}
	}*/
	
	@Override
	public void publish(String msg, Boolean chat){
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message");
			for (BrokerThread sub : subscribers) {
				sub.dispatchMessage(msg,chat);
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
	
	@Override
	public void publishNumberGame(Integer numberGame, int playerId){
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message");
			for (BrokerThread sub : subscribers) {
				sub.updateNumberGame(numberGame);
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
	

	@Override
	public void subscribe(PubSubCommunication o) {
		BrokerThread r = (BrokerThread)o;
		subscribers.add(r);
		System.out.println(subscribers.size());
	}
	

}
