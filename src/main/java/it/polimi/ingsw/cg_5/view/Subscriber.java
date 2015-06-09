package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.BrokerInterface;
import it.polimi.ingsw.cg_5.connection.SubscriberInterface;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Subscriber implements SubscriberInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	/**
	 * 
	 * @param name The name of the subscriber
	 */
	public Subscriber(String name) {
		super();
		this.name = name;
	}

	
	public void connectToBroker(){
		try {
			
			
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 7777);

			BrokerInterface broker = (BrokerInterface) registry.lookup("Broker");

			broker.subscribe((SubscriberInterface)UnicastRemoteObject.exportObject(this,0));
			
		} catch (NotBoundException| RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param msg is the message sent by the broker by invoking subscriber's remote interface
	 * the method simply prints the message received by the broker
	 */
	@Override
	public void dispatchMessage(String msg) {
		System.out.println("Subscriber-"+name+" received message: "+msg);

	}
	

}
