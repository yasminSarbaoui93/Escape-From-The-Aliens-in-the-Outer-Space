package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;

import it.polimi.ingsw.cg_5.model.Alien;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.ItemCardType;
import it.polimi.ingsw.cg_5.model.TurnState;
import it.polimi.ingsw.cg_5.model.Character;

public class UseItemCard extends Action {
	private String sectorToSpotLight;
	protected ItemCardType usingItemCardType;
	
	public UseItemCard(GameState gameState,ItemCardType itemCardType) {
		super(gameState);
		this.usingItemCardType=itemCardType;
		
	}

	@Override
	public void execute() throws NullPointerException{
		

		
		if(usingItemCardType==ItemCardType.ADRENALINE){
			gameState.getCurrentCharacter().setMaxMove(2);
			
			
		}
		
		if(usingItemCardType==ItemCardType.ATTACK){
			gameState.getCurrentCharacter().setCanAttack(true);
		}
		
		if(usingItemCardType==ItemCardType.SEDATIVES){
			// DA SISTEMARE!
			gameState.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
			
		}
		
		
		if(usingItemCardType==ItemCardType.TELEPORT){
			gameState.getCurrentCharacter().getCurrentSector().getCharacterList().remove(gameState.getCurrentCharacter());
			gameState.getCurrentCharacter().setCurrentSector(gameState.getMap().takeSector("HUMAN_START"));
			gameState.getMap().takeSector("HUMAN_START").getCharacterList().add(gameState.getCurrentCharacter());
			/*
			if(gameState.getTurn().getTurnState()==TurnState.HASMOVED)
				gameState.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
			SBAGLIATO SE FAI TELEPORT TI PUOI ANCORA MUOVERE
		*/
		}

	}
	
	/**Controls if the character can use the declared item card. If it's a Human, it can use the card only if it has it in the array list of possessed item cards;
	 * while if it's an Alien, it can never use them, but only hold them; false if the character is an alien or if the human wants to use a card he doesn't haveee
	 * @return True if the character is a Human and he holds the card he wants to use
	 */
	
	public boolean checkAction(){
		if(gameState.getCurrentCharacter().getClass() == Alien.class){
			return false;
		}
		else{
			for(ItemCard playerItemCard : gameState.getCurrentCharacter().getItemPlayerCard()){
				if(playerItemCard.getItemCardType().equals(usingItemCardType)){
					gameState.getCurrentCharacter().getItemPlayerCard().remove(playerItemCard);
					gameState.getItemDeck().getUsedItemDeck().add(playerItemCard);
					return true;
				}
			}
		}
		
		return false;
	}

}
