package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
	private ViewController viewController;

	
	public ButtonListener(ViewController viewController) {
		super();
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
		// TODO Auto-generated method stub
		
	}

}
