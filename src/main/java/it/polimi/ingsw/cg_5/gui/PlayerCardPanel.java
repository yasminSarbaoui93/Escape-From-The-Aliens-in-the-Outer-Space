package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.model.ItemCard;

public class PlayerCardPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	JTextField spot1= new JTextField(10);
	JTextField spot2= new JTextField(10);
	JTextField spot3= new JTextField(10);
	JTextField spot4= new JTextField(10);
	
	
	public PlayerCardPanel(){
		Color backColor=Color.black;
		Color foreColor= Color.green;
		JLabel fistCard = new JLabel(" Fist Card: ");
		JLabel secondCard=new JLabel(" Second Card:");
		JLabel thirdCard= new JLabel(" Third Card:");
		JLabel fourthCard= new JLabel(" Extra Card:");
		fistCard.setForeground(foreColor);
		secondCard.setForeground(foreColor);
		thirdCard.setForeground(foreColor);
		fourthCard.setForeground(foreColor);
		spot1.setForeground(foreColor);
		spot2.setForeground(foreColor);
		spot3.setForeground(foreColor);
		spot4.setForeground(foreColor);
		spot1.setBackground(backColor);
		spot2.setBackground(backColor);
		spot3.setBackground(backColor);
		spot4.setBackground(backColor);
		spot1.setBorder(BorderFactory.createLineBorder(Color.blue));
		spot2.setBorder(BorderFactory.createLineBorder(Color.blue));
		spot3.setBorder(BorderFactory.createLineBorder(Color.blue));
		spot4.setBorder(BorderFactory.createLineBorder(Color.blue));
		spot1.setEditable(false);
		spot2.setEditable(false);
		spot3.setEditable(false);
		spot4.setEditable(false);
		setBackground(backColor);
		setBorder(BorderFactory.createLineBorder(Color.blue));
		setLayout( new GridLayout(4,2));
		setBounds(801,593, 294, 92);
		add(fistCard);
		add(spot1);
		add(secondCard);
		add(spot2);
		add(thirdCard);
		add(spot3);
		add(fourthCard);
		add(spot4);
		
		
	}
	
	private void resetSpots(){
		spot1.setText("");
		spot2.setText("");
		spot3.setText("");
		spot4.setText("");
	}
	
	
	
	public void updatePlayerCards(Character character){
		int cardNumberToUp= character.getItemPlayerCard().size();

				this.resetSpots();
				
				if(cardNumberToUp!=0){
				this.spot1.setText(character.getItemPlayerCard().get(cardNumberToUp-1).toString());
				cardNumberToUp=cardNumberToUp-1;
				}
				if(cardNumberToUp!=0){
				this.spot2.setText(character.getItemPlayerCard().get(cardNumberToUp-1).toString());
				cardNumberToUp=cardNumberToUp-1;
				}
				if(cardNumberToUp!=0){
				this.spot3.setText(character.getItemPlayerCard().get(cardNumberToUp-1).toString());
				cardNumberToUp=cardNumberToUp-1;
				}
				if(cardNumberToUp!=0){
				this.spot4.setText(character.getItemPlayerCard().get(cardNumberToUp-1).toString());
				cardNumberToUp=cardNumberToUp-1;
				}
		
		
	}
}
