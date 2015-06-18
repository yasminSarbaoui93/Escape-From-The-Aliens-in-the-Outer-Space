package it.polimi.ingsw.cg_5.view.subscriber;


import java.awt.Color;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.text.BadLocationException;

import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.view.View;

public class SubscriberRmi implements SubscriberInterfaceRmi, Subscriber, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String brokerName;
	private View view; 

	/**
	 * 
	 * @param name The name of the subscriber
	 * @throws RemoteException 
	 */
	public SubscriberRmi(String name) throws RemoteException {
		super();
		this.name = name;
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		SubscriberInterfaceRmi stub = (SubscriberInterfaceRmi)UnicastRemoteObject.exportObject(this, 0);
		registry.rebind(this.name, stub);
	}

	
	public void setBrokerName(String brokerName){
		this.brokerName=brokerName;
	}
	
	public String getBrokerName(){
		return this.brokerName;
	}
	
	public View getView() {
		return view;
	}


	
	public void setView(View view) throws RemoteException{
		this.view = view;
	}
	


/*  DA CANCELLARE
	public void connectToBroker(){
		try {
			
			
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

		BrokerInterface broker = (BrokerInterface) registry.lookup("Broker");
	
			broker.subscribe((SubscriberInterface)UnicastRemoteObject.exportObject(this,0));
			
		} catch (NotBoundException| RemoteException e) {
			e.printStackTrace();
		}
	} */
	
	/**
	 * @param msg is the message sent by the broker by invoking subscriber's remote interface
	 * the method simply prints the message received by the broker
	 
	 */
	@Override
	public void dispatchMessage(String msg) throws RemoteException  {
		System.out.println("Subscriber-"+name+" received message: "+msg);
		try {
			this.getView().getViewController().getEscape().getLogPanel().updateLogMessage(msg,Color.RED);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateNumberGame(int numberGame) {
		this.view.setNumberGame(numberGame);
	
	}


	@Override
	public void updateCharacter(Character character) throws RemoteException {
		this.view.setCharacter(character);
		System.out.println("Your character for this game will be: "+ this.view.getCharacter());
		
		
		this.view.getViewController().getEscape().getDtoPanel().updateDtoPanel(this.view.getCharacter());
	}
	

}

