package PubSub;

import java.io.Serializable;


public class Subscriber implements SubscriberInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 * @param name The name of the subscriber
	 */
	public Subscriber(String name) {
		super();
		this.name = name;
	}

	private String name;
	
	
	/**
	 * @param msg is the message sent by the broker by invoking subscriber's remote interface
	 * the method simply prints the message received by the broker
	 */
	@Override
	public void dispatchMessage(String msg) {
		System.out.println("Subscriber-"+name+" received message: "+msg);
	}

}
