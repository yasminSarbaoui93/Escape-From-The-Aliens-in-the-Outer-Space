package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class StartButtonListener implements ActionListener {
	private ViewController viewController;
	JTextField userLabel= new JTextField();
	JComboBox<String> listMap=  new JComboBox<String>();
	JComboBox<String> listPlayerNumber =  new JComboBox<String>();
	JComboBox<String> typeConnection =  new JComboBox<String>();

	
	public StartButtonListener(ViewController viewController,JTextField userLabel,JComboBox<String> listMap,
			JComboBox<String> listPlayerNumber,JComboBox<String> typeConnection) {
		super();
		this.listMap=listMap;
		this.userLabel=userLabel;
		this.listPlayerNumber=listPlayerNumber;
		this.typeConnection=typeConnection;
		this.viewController = viewController;
	}
	
	public ViewController getViewController() {
		return viewController;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			String userName = userLabel.getText();
			String  choosenMap= (String) listMap.getSelectedItem();
			String  maxNumberPlayers= (String) listPlayerNumber.getSelectedItem();
			String 	connessionType= (String) typeConnection.getSelectedItem();
			
	
		
			 
				try {
					this.getViewController().ViewCreatorAndSubscribeRequest(userName, choosenMap,maxNumberPlayers,connessionType);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
			
				
			
		
	}

}
