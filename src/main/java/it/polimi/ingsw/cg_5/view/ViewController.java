package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.gui.EscapeFromAlienGame;
import it.polimi.ingsw.cg_5.gui.StartOptions;
import it.polimi.ingsw.cg_5.model.Human;

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
public void ViewCreatorAndSubscribeRequest(String userName, String choosenMap, String maxNumberPlayers, String connessionType) throws Exception{
	this.view= new View(userName);
	view.getSubscriber().setView(this.view);
	this.view.setViewController(this);
	this.view.setPlayerID(view.getRmiClient().matchRequest(choosenMap, Integer.parseInt(maxNumberPlayers), userName));
	
	
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