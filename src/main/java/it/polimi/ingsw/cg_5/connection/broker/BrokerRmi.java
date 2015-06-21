package it.polimi.ingsw.cg_5.connection.broker;



import it.polimi.ingsw.cg_5.view.subscriber.SubscriberInterfaceRmi;

import java.rmi.RemoteException;
import java.util.ArrayList;



public class BrokerRmi extends Broker {

	private  ArrayList<SubscriberInterfaceRmi> subscribers = new ArrayList<SubscriberInterfaceRmi>();
	private String topic;
	public ArrayList<SubscriberInterfaceRmi> getSubscribers() {
		return subscribers;
	}

	public BrokerRmi(String topic){
		this.topic = topic;
	
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * 
	 * @param msg - message to be published to all the subscribers
	 * This is not a remote method, however it calls the remote 
	 * method dispatchMessage for each Subscriber.  
	 * @throws RemoteException 
	 */
	public void publish(String msg) throws RemoteException{
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message on topic "+topic);
			for (SubscriberInterfaceRmi sub : subscribers) {
				
					sub.dispatchMessage(msg);
				
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
	
	public void publishNumberGame(int numberGame,int playerId){
		if(!subscribers.isEmpty()){
			for (SubscriberInterfaceRmi sub : subscribers) {
				try {
					sub.updateNumberGame(numberGame);
					sub.updatecurrentPlayerId(playerId);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
	

	
	
	/**
	 * @param r is the Subcriber's remote interface that the broker can use to publish messages
	 * The method updates the list of subscriber interfaces that are subscribed to the broker
	 */
	
	public void subscribe(SubscriberInterfaceRmi r) {
		subscribers.add(r);
	}
}
