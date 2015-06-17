package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import it.polimi.ingsw.cg_5.model.Character;


public class DtoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	final String defautString="Not Assigned Yet";
	JLabel username = new JLabel("Username :");
	JTextField name = new JTextField(10);
	JLabel playerID = new JLabel("PlayerID :");
	JTextField ID = new JTextField(10);
	JLabel characterName= new JLabel("Character Name");
	JTextField role= new JTextField(10);
	JLabel currentSector= new JLabel("Current Sector");
	JTextField sector =new  JTextField(10);
	JLabel currentPlayer= new JLabel("Current Player");
	JTextField currentPL =new  JTextField(10);
	
	
	
	 public DtoPanel() {
		 setLayout( new GridLayout(5,2));
		 
		 add(username);
		 add(name);
		 add(playerID);
		 add(ID);
		 add(characterName);
		 add(role);
		 add(currentSector);
		 add(sector);
		 add(currentPlayer);
		 add(currentPL);
		 name.setText(defautString);
		 role.setEditable(false);
		 role.setText(defautString);
		 name.setEditable(false);
		 sector.setEditable(false);
		 sector.setText(defautString);
		 ID.setText(defautString);
		 ID.setEditable(false);
		 currentPL.setEditable(false);
		 currentPL.setText(defautString);
		 setBounds(800, 301, 294, 120);
		 setBackground(Color.WHITE);
		 setBorder(BorderFactory.createLineBorder(Color.red));
		 
	}
	
	@Override
	public Component add(Component comp) {
				return super.add(comp);
	}
	
	
	public void updateDtoPanel(Character character){
		
		this.sector.setText(character.getCurrentSector() +"\n");
		this.ID.setText(character.getPlayerID() +"\n");
		this.role.setText(character.getName() +"\n");
		
	}
	
	
}
