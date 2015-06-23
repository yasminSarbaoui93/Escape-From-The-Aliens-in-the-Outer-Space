package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.model.DangerousSector;
import it.polimi.ingsw.cg_5.model.Human;
import it.polimi.ingsw.cg_5.model.SafeSector;
import it.polimi.ingsw.cg_5.model.TurnState;
import it.polimi.ingsw.cg_5.model.Character;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel{
	
	
	private GameButton attackButton= new GameButton("Attack");
	private GameButton drawCard=new GameButton("DrawCard");
	
	private GameButton useCardButton= new GameButton("UseCard");
	private GameButton endTurn= new GameButton("endTurn");
	private GameButton discard = new GameButton("Discard");
	private GameButton buttonFake = new GameButton("");
	
	public GameButton getAttackButton() {
		return attackButton;
	}

	public GameButton getDrawCard() {
		return drawCard;
	}



	public GameButton getUseCardButton() {
		return useCardButton;
	}

	public GameButton getEndTurn() {
		return endTurn;
	}

	public GameButton getDiscard() {
		return discard;
	}

	public GameButton getButtonFake() {
		return buttonFake;
	}

	public ButtonPanel(){
		
		setBorder(BorderFactory.createLineBorder(Color.blue));			
		setBounds(800,421, 294, 264);
		setBackground(Color.BLACK);
		setLayout( new GridLayout(4,2));
		add(attackButton);
		add(drawCard);	
		add(useCardButton);	
		add(endTurn);
	    add(discard);
		add(buttonFake);
	}
	
	/** Metodo che in base al turnState abilita/disabilita i button che è possibile premere
	 * @param turnState
	 * @param playerDTO
	 */
	public void buttonsSetter(TurnState turnState,PlayerDTO playerDTO){
		
	
		attackButton.setEnabled(false);
		
		drawCard.setEnabled(false);
		endTurn.setEnabled(false);
		discard.setEnabled(false);
		buttonFake.setEnabled(false);
		useCardButton.setEnabled(false);
		
		
		if(playerDTO.getYourCharacter().getClass()==Human.class)
			useCardButton.setEnabled(true);
	
		
		if(turnState==TurnState.STARTED ){
			this.useCardButton.setEnabled(true);	
		}
		
		if(turnState==TurnState.HASMOVED&& playerDTO.getYourCharacter().getCurrentSector().getClass()==DangerousSector.class){
			drawCard.setEnabled(true);
			if(playerDTO.getYourCharacter().isCanAttack())
				attackButton.setEnabled(true);
		}
		if(turnState==TurnState.HASMOVED&& playerDTO.getYourCharacter().getCurrentSector().getClass()==SafeSector.class){
			if(playerDTO.getYourCharacter().isCanAttack())
				attackButton.setEnabled(true);
			    endTurn.setEnabled(true);
		}
		if(turnState==TurnState.HASATTACKORDRAWN){
			endTurn.setEnabled(true);
		}
		
		if(turnState==TurnState.BLUFFING ){
		useCardButton.setEnabled(false);
		endTurn.setEnabled(true);
		}
		
		if(playerDTO.getYourCharacter().getItemPlayerCard().size()==4){
			discard.setEnabled(true);
		}
		
			
	}
	
	
}
