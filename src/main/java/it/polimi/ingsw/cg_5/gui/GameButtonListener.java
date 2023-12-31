package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.view.ViewController;
import it.polimi.ingsw.cg_5.model.Human;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

/**Implements the actionListener. Useful to "listen" to the game buttons with additive patameters needed to update the screan panels.
 * @author Andrea
 *
 */
public class GameButtonListener implements ActionListener {
	ViewController viewController;

	Confines confine=new Confines();
	DtoPanel dtoPanel;

	String actionType;
	String sector;
	LogMessage logPanel;
	
	public ViewController getViewController() {
		return viewController;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}	

	public GameButtonListener(ViewController viewController, DtoPanel dtoPanel,LogMessage logPanel,
			 String actionType, String sector) {
		super();
		this.viewController = viewController;
		this.dtoPanel = dtoPanel;
		this.logPanel=logPanel;
		this.actionType=actionType;
		this.sector=sector;
	}

	/* (non-Javadoc)
	 * Give an input action, it calls the client method that allouds to communicate to the server the choosen action by the player.
	 * Moreover, it prints the piece on the right sector if a move is performed, using the metods of the Confines class.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		 
	String result=null;
	PlayerDTO playerDTO=new PlayerDTO(new Human("prova",10000));
				
	try {
		if(actionType.equals("MOVE")){
		playerDTO =this.getViewController().getView().getClient().moveRequest(sector,this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("DRAW")){
			playerDTO =this.getViewController().getView().getClient().drawCardRequest(this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("ATTACK")){
			playerDTO =this.getViewController().getView().getClient().attackRequest(this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("BLUFF")){
			
			playerDTO =this.getViewController().getView().getClient().bluffRequest(sector,this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("ENDTURN")){
			playerDTO =this.getViewController().getView().getClient().endTurnRequest(this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		if(actionType.equals("USECARD")){
			String card= JOptionPane.showInputDialog("Card to use");
			if (card.toUpperCase().equals("SPOTLIGHT")){
				String sector =JOptionPane.showInputDialog("Sector to spot");
				
				playerDTO =this.getViewController().getView().getClient().useSpotLightRequest(card.toUpperCase(),this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame(),sector.toUpperCase());
				}	
				
			else{
			playerDTO =this.getViewController().getView().getClient().useCardRequest(card.toUpperCase(),this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
			}
			}
		if(actionType.equals("DISCARD")){
			String discard= JOptionPane.showInputDialog("Card to discard");
			playerDTO =this.getViewController().getView().getClient().discardRequest(discard.toUpperCase(),this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
		}
		
		
		viewController.getView().setCharacter(playerDTO.getYourCharacter());
		
		
		if(playerDTO.getYourCharacter()!=null){
		viewController.getEscape().getDtoPanel().updateDtoPanel(viewController.getView().getCharacter());
		viewController.getEscape().getDtoPanel().updateDtoPanelCurrentId(viewController.getView().getCurrentPlayerId());
		viewController.getEscape().getPlayerCardPanel().updatePlayerCards(viewController.getView().getCharacter());

		viewController.getEscape().getButtonPanel().buttonsSetter(playerDTO.getTurnState(), playerDTO);
		result= playerDTO.getMessageToSend();
		//sposto la pedina nel giusto settore!
		String sector = viewController.getView().getCharacter().getCurrentSector().getSectorName();
		char col= sector.charAt(0);
		String colnum = sector.substring(0,1);
		int colnumb = (int) colnum.charAt(0)-(int)'A'+1;
		String row= sector.substring(1, 3);
		int x=(int)this.confine.getXCoordinateFromLetter(col);
		int y;
		if(colnumb%2==1){
		y=(int)this.confine.getYOddCoordinateFromLetter(row);
		}
		else{
			y=(int)this.confine.getYevenCoordinateFromLetter(row);
		}
		this.viewController.getEscape().printPiece(x, y);
		}
		else{
			result= playerDTO.getMessageToSend();	
		}
	
	} catch (Exception e1) {
		e1.getMessage();
	}
			try {
				viewController.getEscape().getLogPanel().updateLogMessage(result,Color.GREEN);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}				
			 
	}

}


