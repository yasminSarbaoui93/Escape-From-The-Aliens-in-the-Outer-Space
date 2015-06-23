package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**classe che implementa actionlistener ma che ha altri parametri utilizzare per aggiornare lo stato dei pannelli di gioco
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
	 * schiatto il bottone di send il messaggio viene inviato al server che provvederà a inoltrarlo in broadcast tramite
	 * la publish
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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
