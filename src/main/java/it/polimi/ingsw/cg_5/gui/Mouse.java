package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Image;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;


/**classe che implementa mouselistener e ci permette di utilizzare il mouse per eseguire alcune azioni
 * @author Andrea
 *
 */
public class Mouse implements MouseListener {
	JLabel Label = new JLabel("Coordinates");
	Confines confine = new Confines();
	EscapeFromAlienGame escape;
	
	public  Mouse(EscapeFromAlienGame escape){
		this.escape=escape;
	
	
	
	Label.setBounds(100, 100, 0, 0);
	Label.setBackground(Color.GREEN);
	Label.setVisible(true);
	//costruzione lista colonne
	
	
	}
	

	
	/* (non-Javadoc)
	 * metodo che decide che cosa fare dopo aver premuto uno dei tasti del mouse, nel caso venga premuto il tasto sinistro
	 * verrà disegnato un cerchio intorno al settore selezionato, se viene premuto il tasto destro si aprirà un menù a 
	 * tendina con le scelte che sarà possibile fre
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */ 
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			 JMenuItem moveItem;
			    JPopupMenu rightMenu= new JPopupMenu();
			    rightMenu.setBackground(Color.BLACK);
			        moveItem= new JMenuItem("Move Here");
			        moveItem.addActionListener(new GameButtonListener(this.escape.getViewController(),this.escape.getDtoPanel(),this.escape.logPanel,
			        		"MOVE",printPosition(e)));
			        rightMenu.add(moveItem);
			       JMenuItem bluffItem= new JMenuItem("Bluff");
			       bluffItem.addActionListener((new GameButtonListener(this.escape.getViewController(),this.escape.getDtoPanel(),this.escape.logPanel,
			        		"BLUFF",printPosition(e))));
			       rightMenu.add(bluffItem);
			        rightMenu.show(e.getComponent(), e.getX(), e.getY());
			    }
		if(SwingUtilities.isLeftMouseButton(e)){
			printPosition(e);
		}
		}
	
		/** metodo che dato un evento di click del mouse va a stampare il cerchio sopra citato facendo in modo che sia
		 * centrato nel settore in cui si è cliccato
		 * @param e
		 * @return
		 */
		public String printPosition(MouseEvent e){
			char column= '/';
			String row = "0";
			double exactX =0;
			double exactY = 0;
			
		
			for(LineConfines searchColumn: this.confine.getColumnlist()){
				if(e.getPoint().getX()>searchColumn.getMinValue() && e.getPoint().getX()<searchColumn.getMaxValue()){
					column=(char)searchColumn.getLine();
					
					exactX=searchColumn.getMinValue()+(searchColumn.getMaxValue()-searchColumn.getMinValue())/2;
				}	
				
			}
			if((int)column%2==1){
			for(LineConfines searchLetter: this.confine.getOddrowlist()){
				if(e.getPoint().getY()>searchLetter.getMinValue() && e.getPoint().getY()<searchLetter.getMaxValue()){
					row=(String)searchLetter.getLine();
					exactY=searchLetter.getMinValue()+(searchLetter.getMaxValue()-searchLetter.getMinValue())/2;
					
				}	
				
			}
			}
			else{
				for(LineConfines searchLetter: this.confine.getEvenrowlist()){
					if(e.getPoint().getY()>searchLetter.getMinValue() && e.getPoint().getY()<searchLetter.getMaxValue()){
						row=(String)searchLetter.getLine();
						exactY=searchLetter.getMinValue()+(searchLetter.getMaxValue()-searchLetter.getMinValue())/2;
					}	
					
				}
				
			}
			
		
			
			Double xMousePosition = e.getPoint().getX();
			Double yMousePosition = e.getPoint().getY();
			Label.setText("X " + xMousePosition + " Y "+ yMousePosition
					+" Column "+column +" Row" + row);
			this.escape.printCircle((int)exactX-32, (int)exactY-55);
			
			// tasto destro
			
			return column+row;
			
			}
			
			
		
		
		

		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}
