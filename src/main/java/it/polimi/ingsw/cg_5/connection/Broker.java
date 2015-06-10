package it.polimi.ingsw.cg_5.connection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Broker implements BrokerInterface{

	
	
	private  ArrayList<SubscriberInterface> subscribers = new ArrayList<SubscriberInterface>();
	private String topic;
	
	public ArrayList<SubscriberInterface> getSubscribers() {
		return subscribers;
	}

	public Broker(String topic){
		this.topic = topic;
		
		try {
			
			Registry registry = LocateRegistry.createRegistry(7777);
			BrokerInterface stub = (BrokerInterface)UnicastRemoteObject.exportObject(this, 0);
			registry.rebind("Broker", stub);
		
			System.out.println("Broker avviato, in attesa di subscribers...");
			//registry.unbind("Broker"); -> DA SPOSTARE NEL MOMENTO IN CUI IL MATCH VIENE CREATO !
			//UnicastRemoteObject.unexportObject(this, true);		
		}
		catch (RemoteException | NoSuchElementException e) {
			e.printStackTrace();
		}
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
	 */
	public void publish(String msg){
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message on topic "+topic);
			for (SubscriberInterface sub : subscribers) {
				try {
					sub.dispatchMessage(msg);
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
	public void subscribe(SubscriberInterface r) {
		subscribers.add(r);
	}
}
