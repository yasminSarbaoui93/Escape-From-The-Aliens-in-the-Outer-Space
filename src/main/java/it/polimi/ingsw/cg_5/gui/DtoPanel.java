package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import it.polimi.ingsw.cg_5.model.Character;


public class DtoPanel extends JPanel{
	
	JLabel username = new JLabel("Username");
	JLabel name = new JLabel("not Assigned yet\n");
	JLabel currentSector= new JLabel("Current Sector");
	JLabel	sector = new JLabel("--------\n");
	
	
	
	 public DtoPanel() {
		 add(username);
		 add(name);
		 add(currentSector);
		 add(sector);
		 setBounds(830, 310, 250, 100);
		 setBackground(Color.WHITE);
	}
	
	@Override
	public Component add(Component comp) {
				return super.add(comp);
	}
	
	
	public void updateDtoPanel(Character character){
		this.name.setText(character.getName()+"\n");
		this.sector.setText(character.getCurrentSector() +"\n");
		
	}
	
	
}
