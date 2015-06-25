package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Class that implements the action listener and has other parameters used to update the game panels state
 * @author Andrea
 *
 */
public class MessageListener implements ActionListener {
	private String message;
	private ViewController viewController;
	private MessagePanel panel;
	
	public MessageListener(ViewController viewController, MessagePanel panel) {
		super();
		this.panel=panel;
		this.viewController = viewController;
	}

	/* (non-Javadoc)
	 * Once pushed the send button, the message is forwarded to the server that will provide to send it broadcast using
	 * the broker.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			message="PLAYER - "+viewController.getView().getCharacter().getPlayerID() + "- "+ panel.getMessageBox().getText()+"\n";
			panel.getMessageBox().setText("");
			this.viewController.getView().getClient().sendmessageRequest(message, viewController.getView().getCharacter().getPlayerID()
					, viewController.getView().getNumberGame());

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
