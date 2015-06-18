package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.gui.EscapeFromAlienGame;
import it.polimi.ingsw.cg_5.gui.StartOptions;
import it.polimi.ingsw.cg_5.view.subscriber.Subscriber;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberRmi;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberSocket;
public class ViewController {
	private StartOptions startOptions;
	public StartOptions getStartOptions() {
		return startOptions;
	}
	private EscapeFromAlienGame escape;
	private View view;
	
	

public ViewController (){
	startOptions= new StartOptions(this);
	escape= new EscapeFromAlienGame(this);
	startOptions.setVisible(true);
	
	
	
}
public void ViewCreatorAndSubscribeRequest(String userName, String choosenMap, String maxNumberPlayers, String connectionType) throws Exception{
	Client client;
	Subscriber subscriber;
	if(connectionType.toUpperCase().equals("SOCKET")){
		client = new SocketClient("127.0.0.1", 1337);
		subscriber = new SubscriberSocket();
	}
	
	else{
		client = new RmiClient();
		subscriber = new SubscriberRmi(userName);
		
	}	
	this.view= new View(userName, client, subscriber);
	view.getSubscriber().setView(this.view);
	this.view.setViewController(this);
	this.view.setPlayerID(view.getClient().matchRequest(choosenMap, Integer.parseInt(maxNumberPlayers), userName));
	
	
	escape.setVisible(true);
	startOptions.setVisible(false);
	System.out.println(view.getCharacter());
}
public EscapeFromAlienGame getEscape() {
	return escape;
}
public View getView() {
	return view;
}
public void setView(View view) {
	this.view = view;
}

}