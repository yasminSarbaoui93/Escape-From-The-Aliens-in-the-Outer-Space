package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;


/**Implements the mouseListener to abilitate the mouse and execute some actions.
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
	}
	

	
	/* (non-Javadoc)
	 *It decides what to do after some mouse buttons are pushed. In case the left button is pushed, a circle will be printed around
	 *the selected sector, while if the right button is pushed, a menù will be opened with the possible choices.
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */ 
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			 JMenuItem moveItem;
			    JPopupMenu rightMenu= new JPopupMenu();
			    rightMenu.setBackground(Color.BLACK);
			        moveItem= new JMenuItem("Move Here");
			        moveItem.addActionListener(new GameButtonListener(this.escape.getViewController(),this.escape.getDtoPanel(),this.escape.getLogPanel(),
			        		"MOVE",printPosition(e)));
			        rightMenu.add(moveItem);
			       JMenuItem bluffItem= new JMenuItem("Bluff");
			       bluffItem.addActionListener((new GameButtonListener(this.escape.getViewController(),this.escape.getDtoPanel(),this.escape.getLogPanel(),
			        		"BLUFF",printPosition(e))));
			       rightMenu.add(bluffItem);
			        rightMenu.show(e.getComponent(), e.getX(), e.getY());
			    }
		if(SwingUtilities.isLeftMouseButton(e)){
			printPosition(e);
		}
		}
	
		/**Given a click event of the mouse, it prints the circle at the center of the clicked sector.
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
			return column+row;
			
			}
		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
}
