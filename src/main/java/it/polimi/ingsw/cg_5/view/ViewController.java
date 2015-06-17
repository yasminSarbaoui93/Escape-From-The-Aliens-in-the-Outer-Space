package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.gui.EscapeFromAlienGame;
import it.polimi.ingsw.cg_5.gui.StartOptions;

public class ViewController {
	private StartOptions startOptions;
	private EscapeFromAlienGame escape;
	private View view;
	
	


public ViewController (){
	startOptions= new StartOptions(this);
	startOptions.setVisible(true);
	
	
}
public void ViewCreatorAndSubscribeRequest(String userName, String choosenMap, String maxNumberPlayers, String connessionType) throws Exception{
	this.view= new View(userName);
	view.getSubscriber().setView(view);
	
	view.getRmiClient().matchRequest(choosenMap, Integer.parseInt(maxNumberPlayers), userName);
	this.view.setViewController(this);
	escape= new EscapeFromAlienGame(this);
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