package it.polimi.ingsw.cg_5.connection;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ButtonsPanel extends JPanel{
	JButton moveButton= new JButton("Move");
	JButton attackButton= new JButton("Attack");
	JButton drawCard=new JButton("DrawCard");
	JButton bluffButton= new JButton("Bluff Sector");
	JButton useCardButton= new JButton("UseCard");
	JButton endTurn= new JButton("endTurn");
	JButton discard = new JButton("Discard");
	JButton buttonFake = new JButton();
	private final Color buttonBackGColor= Color.BLACK;
	private final Color buttonColor = Color.green;
	Border buttonBorder = new LineBorder(Color.blue, 1);
	public ButtonsPanel(){
		
		
		moveButton.setBackground(buttonBackGColor);
		moveButton.setForeground(buttonColor);
		moveButton.setBorder(buttonBorder);
		drawCard.setBackground(buttonBackGColor);
		drawCard.setForeground(buttonColor);
		drawCard.setBorder(buttonBorder);
		bluffButton.setBackground(buttonBackGColor);
		bluffButton.setForeground(buttonColor);
		bluffButton.setBorder(buttonBorder);
		useCardButton.setBackground(buttonBackGColor);
		useCardButton.setForeground(buttonColor);
		useCardButton.setBorder(buttonBorder);
		endTurn.setBackground(buttonBackGColor);
		endTurn.setForeground(buttonColor);
		endTurn.setBorder(buttonBorder);
		discard.setBackground(buttonBackGColor);
		discard.setForeground(buttonColor);
		discard.setBorder(buttonBorder);
		attackButton.setBackground(buttonBackGColor);
		attackButton.setForeground(buttonColor);
		attackButton.setBorder(buttonBorder);
		
	    add(moveButton);
		add(attackButton);
	    add(drawCard);
		add(bluffButton);	
		add(useCardButton);
		add(bluffButton);	
		add(endTurn);
		add(discard);
		add(buttonFake);
		
	}

}
