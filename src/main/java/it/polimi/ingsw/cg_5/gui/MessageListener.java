package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MessageListener implements ActionListener {
	String message;
	ViewController viewController;
	MessagePanel panel;
	
	public MessageListener(ViewController viewController, MessagePanel panel) {
		super();
		this.panel=panel;
		this.viewController = viewController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			message="PLAYER - "+viewController.getView().getCharacter().getPlayerID() + "- "+ panel.getMessageBox().getText()+"\n";
			panel.getMessageBox().setText("");
			this.viewController.getView().getClient().sendmessageRequest(message, viewController.getView().getCharacter().getPlayerID()
					, viewController.getView().getNumberGame());
			
		
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
