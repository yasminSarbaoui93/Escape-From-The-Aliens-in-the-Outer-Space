package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.gui.EscapeFromAlienGame;
import it.polimi.ingsw.cg_5.gui.StartOptions;
import it.polimi.ingsw.cg_5.view.subscriber.Subscriber;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberRmi;
import it.polimi.ingsw.cg_5.view.subscriber.SubscriberSocket;
/**Class that collects all the elements caratterizing the client, going from the view to the game screan.
 * @author Andrea
 *
 */
public class ViewController {
	private StartOptions startOptions;
	public StartOptions getStartOptions() {
		return startOptions;
	}
	private EscapeFromAlienGame escape;
	private View view;
	
	

public ViewController (){
	startOptions= new StartOptions(this);
	startOptions.setVisible(true);
	
}
/**Method called when a client clicks on the start button. This creates a client associated to the view that matches the
 * connectionType choosen. Moreover it sends the request to join a new game to the server.
 * @param userName
 * @param choosenMap
 * @param maxNumberPlayers
 * @param connectionType
 * @throws Exception
 */
public void ViewCreatorAndSubscribeRequest(String userName, String choosenMap, String maxNumberPlayers, String connectionType) throws Exception{
	Client client;
	Subscriber subscriber;
	if(connectionType.toUpperCase().equals("SOCKET")){
		client = new SocketClient("127.0.0.1", 7777);
		subscriber = new SubscriberSocket(userName);
	}
	
	else{
		client = new RmiClient();
		subscriber = new SubscriberRmi(userName);
		
	}	
	escape= new EscapeFromAlienGame(this);
	escape.getDtoPanel().updateNameDtoPanel(userName);
	this.view= new View(userName, client, subscriber);
	view.getSubscriber().setView(this.view);
	
	this.view.setViewController(this);
	this.view.setPlayerID(view.getClient().matchRequest(choosenMap, Integer.parseInt(maxNumberPlayers), userName, connectionType));
	
	System.out.println("VIEW CONTROLLER, player id: "+view.getPlayerID()+"NUMERO GIOCO: "+view.getNumberGame());
	
	escape.setVisible(true);
	startOptions.setVisible(false);
	
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
