package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.view.ViewController;
import it.polimi.ingsw.cg_5.model.Human;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

public class GameButtonListener implements ActionListener {
	ViewController viewController;
	DtoPanel dtoPanel;
	LogMessage logPanel;
	String actionType;
	
	public ViewController getViewController() {
		return viewController;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

	

	public GameButtonListener(ViewController viewController, DtoPanel dtoPanel,
			LogMessage logPanel, String actionType) {
		super();
		this.viewController = viewController;
		this.dtoPanel = dtoPanel;
		this.logPanel = logPanel;
		this.actionType=actionType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
	String result=null;
	PlayerDTO playerDTO=new PlayerDTO(new Human("prova",10000));
				
	try {
		if(actionType.equals("MOVE")){
		String sector= JOptionPane.showInputDialog("Sector to move");
		playerDTO =this.getViewController().getView().getClient().moveRequest(sector,this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("DRAW")){
			playerDTO =this.getViewController().getView().getClient().drawCardRequest(this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("ATTACK")){
			playerDTO =this.getViewController().getView().getClient().attackRequest(this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("BLUFF")){
			String bluff= JOptionPane.showInputDialog("Sector to Bluff");
			playerDTO =this.getViewController().getView().getClient().bluffRequest(bluff,this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("ENDTURN")){
			playerDTO =this.getViewController().getView().getClient().endTurnRequest(this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("USECARD")){
			String card= JOptionPane.showInputDialog("Card to use");
			playerDTO =this.getViewController().getView().getClient().useCardRequest(card,this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("DISCARD")){
			String discard= JOptionPane.showInputDialog("Card to discard");
			playerDTO =this.getViewController().getView().getClient().discardRequest(discard,this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		
		
		viewController.getView().setCharacter(playerDTO.getYourCharacter());
		
		if(playerDTO.getYourCharacter()!=null){
		dtoPanel.updateDtoPanel(viewController.getView().getCharacter());
		result= playerDTO.getMessageToSend();
		}
		else{
			result= playerDTO.getMessageToSend();	
		}
	} catch (RemoteException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
			try {
				logPanel.updateLogMessage(result,Color.GREEN);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
				
			 
		 }

	}


