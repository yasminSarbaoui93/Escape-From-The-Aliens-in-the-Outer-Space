package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.view.ViewController;



public class MainProvaGUI {
	
	public static void main(String[] args){
		
      // EscapeLog escapeLog=new EscapeLog();
		
		//escapeLog.setVisible(true);
		
		//StartOptions startOptions=new StartOptions(new ViewController());
	//startOptions.setVisible(true);
		
		EscapeFromAlienGame escape = new EscapeFromAlienGame(new ViewController());
		escape.setVisible(true);
	}

}
