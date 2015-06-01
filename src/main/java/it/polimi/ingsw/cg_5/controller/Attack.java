package it.polimi.ingsw.cg_5.controller;
import java.util.ArrayList;

import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;


public class Attack extends Action {

	
	public Attack (GameState gameState){
	super(gameState);
	}
	
	@Override
	public void execute() {
		// The attack can be done after the character moved to the new current sector. So if in the current sector there's only the current carachter
		//the attack will not be succesful
		if (gameState.getCurrentCharacter().getCurrentSector().getCharacterList().size()==1){

		}
		else {
		ArrayList <Character> CharacterToKill = new ArrayList <Character> ();
		CharacterToKill.addAll(gameState.getCurrentCharacter().getCurrentSector().getCharacterList());
		//essendo il player che attacck nella sua posizione lo dobbiamo rimuovere
		CharacterToKill.remove(gameState.getCurrentCharacter());
		ArrayList<Character> safeCharacter=new ArrayList<Character>();
		
		ItemCard defenceCard = null;
		
		for(Character character: CharacterToKill){
	    	for(ItemCard itemCard:character.getItemPlayerCard()){
	    		if(itemCard.getItemCardType()==ItemCardType.DEFENCE){
	    			safeCharacter.add(character);
	    			defenceCard=itemCard;
	    		}
	    	}
	    	
	    }
	
		//If the list of safe players is not empty, this means that they've used the defence card, so this has to be put in the used item deck
    	if(!safeCharacter.isEmpty()){
    		safeCharacter.get(0).getItemPlayerCard().remove(defenceCard);
    		CharacterToKill.removeAll(safeCharacter);
    		gameState.getItemDeck().getUsedItemDeck().add(defenceCard);
	    }
    	
    	//removes the characters with no defence card that were killed
		gameState.getCharacterList().removeAll(CharacterToKill);
	    	
	    	
	    }
	    	
	    
	    }
		
		
	/**This method checks if the character that is willing to attack can really do it. If the character is a human, it can attack only if he has the item card to do it,
	 * while if the character is an alien, he can always attack if he already moved to the sector where he wants to attack.
	 * @return True if the character can attack; false if the character is not allowd to attack.
	 */
	public boolean checkAttack(){
			if(gameState.getCurrentCharacter().getClass()==Human.class){
				if(gameState.getTurn().getTurnState().equals(TurnState.HASMOVED) && gameState.getCurrentCharacter().isCanAttack())
					return true;
			}
			if(gameState.getCurrentCharacter().getClass()==Alien.class){
				if(gameState.getTurn().getTurnState().equals(TurnState.HASMOVED))
					return true;
			}
			return false;
			 
			
		}
		
		
	}
	


