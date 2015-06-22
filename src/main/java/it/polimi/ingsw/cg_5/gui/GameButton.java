package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GameButton extends JButton{
	
	private final Color buttonBackGColor= Color.BLACK;
	private final Color buttonColor = Color.green;
	private final Border buttonBorder = new LineBorder(Color.blue, 1);
	
	public GameButton(String buttonName){
		
		super(buttonName);
		setBorder(buttonBorder);
		setBackground(buttonBackGColor);
		setForeground(buttonColor);
		
		
	
	}
	
	
}
