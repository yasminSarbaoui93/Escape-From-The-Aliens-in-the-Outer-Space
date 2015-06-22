package it.polimi.ingsw.cg_5.connection.broker;



import it.polimi.ingsw.cg_5.view.subscriber.SubscriberInterfaceRmi;

import java.rmi.RemoteException;
import java.util.ArrayList;



public class BrokerRmi implements Broker {

	private  ArrayList<SubscriberInterfaceRmi> subscribers = new ArrayList<SubscriberInterfaceRmi>();
	private String topic;
	
	

	public BrokerRmi(String topic){
		this.topic = topic;
	
	}
	
	public ArrayList<SubscriberInterfaceRmi> getSubscribers() {
		return subscribers;
	}
	
	public String getTopic() {
		return topic;
	}

	@Override
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * 
	 * @param msg - message to be published to all the subscribers
	 * This is not a remote method, however it calls the remote 
	 * method dispatchMessage for each Subscriber.  
	 */
	
	@Override
	public void publish(String msg){
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message on topic "+topic);
			for (SubscriberInterfaceRmi sub : subscribers) {
				
					try {
						sub.dispatchMessage(msg);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
	@Override
	public void publishNumberGame(Integer numberGame,int playerId){
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
	@Override
	public void subscribe(PubSubCommunication o) {
		SubscriberInterfaceRmi r = (SubscriberInterfaceRmi)o;
		subscribers.add(r);
	}
}
