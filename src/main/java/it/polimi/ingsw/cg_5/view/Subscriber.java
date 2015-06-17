package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.SubscriberInterface;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.swing.text.BadLocationException;

import it.polimi.ingsw.cg_5.model.Character;

public class Subscriber implements SubscriberInterface, Serializable {

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
	 */
	public Subscriber(String name) {
		super();
		this.name = name;
		//view ) new View(name);
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



	public void setView(View view) {
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
		//this.getView().getViewController().getEscape().updateDocument(msg);

	}

	@Override
	public void updateNumberGame(int numberGame) {
		this.view.setNumberGame(numberGame);
	
	}


	@Override
	public void updateCharacter(Character character) throws RemoteException {
		this.view.setCharacter(character);
		System.out.println("Your character for this game will be: "+ this.view.getCharacter());
	}
	

}

