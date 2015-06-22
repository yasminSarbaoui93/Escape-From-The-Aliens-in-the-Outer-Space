package it.polimi.ingsw.cg_5.view.subscriber;

import it.polimi.ingsw.cg_5.view.View;

public class SubscriberSocket implements Subscriber{
	
	private SubscriberThread subscriberThread;;
	private String name;
	private View view;
	
	public SubscriberSocket(String name){
		super();
		this.name = name;		
		subscriberThread = new SubscriberThread(name);
		subscriberThread.start();
	}
	
	
	public void setView(View view){
		this.view = view;		
	}
	
	public View getView(){
		return this.view;
	}



}
