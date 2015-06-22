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

public class SubscriberRmi implements SubscriberInterfaceRmi, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String brokerName;
	private View view; 

	/**Creates a new subscribere that makes the rebind on the same registry of the broker with its name.	 * 
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
	
	
	/**
	 * @param msg is the message sent by the broker by invoking subscriber's remote interface
	 * the method simply prints the message received by the broker
	 
	 */
	@Override
	public void dispatchMessage(String msg, Boolean chat) throws RemoteException  {
		System.out.println("Subscriber-"+name+" received message: "+msg);
		try {
			if(chat==false){
			this.getView().getViewController().getEscape().getLogPanel().updateLogMessage(msg,Color.RED);
			}
			else{
				
				this.getView().getViewController().getEscape().getMessagePanel().updateChatMessage(msg);
			}
		}catch (BadLocationException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void updateNumberGame(Integer numberGame) throws RemoteException {
		this.view.setNumberGame(numberGame);
	
	}
	public void updatecurrentPlayerId(int playerId) {
		this.view.setCurrentPlayerId(playerId);
		this.view.getViewController().getEscape().getDtoPanel().updateDtoPanelCurrentId(this.view.getCurrentPlayerId());

	}

	@Override
	public void updateCharacter(Character character) throws RemoteException {
		this.view.setCharacter(character);
		System.out.println("Your character for this game will be: "+ this.view.getCharacter());

		
		//this.view.getViewController().getEscape().getDtoPanel().updateDtoPanelCurrentId(this.view.getCurrentPlayerId());
		this.view.getViewController().getEscape().getDtoPanel().updateDtoPanel(this.view.getCharacter());

	}


	

}

