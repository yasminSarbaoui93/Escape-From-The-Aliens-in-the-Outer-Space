package it.polimi.ingsw.cg_5.controller;
import java.util.ArrayList;

import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;


public class Attack extends Action {
	Sector attackedSector;
	
	public Attack (GameState gameState){
	super(gameState);
	}
	
	@Override
	public void execute() {
	
		if (gameState.getCurrentCharacter().getCurrentSector().getCharacterList().isEmpty()){
			//il giocatore ha attaccato in A00, l'attacco non è andato a buon fine
		}
		else {
		ArrayList <Character> deadCharacter = new ArrayList <Character> ();
		deadCharacter.addAll(gameState.getCurrentCharacter().getCurrentSector().getCharacterList());
		ArrayList<Character> safeCharacter=new ArrayList<Character>();
		ItemCard defenceCard=new ItemCard(ItemCardType.DEFENCE);
		for(Character character: deadCharacter){
	    	for(ItemCard itemCard:character.getItemPlayerCard()){
	    		if(itemCard.getItemCardType()==ItemCardType.DEFENCE){
	    			safeCharacter.add(character);
	    		}
	    	}
	    	}
	    	if(!safeCharacter.isEmpty()){
	    		safeCharacter.get(0).getItemPlayerCard().remove(defenceCard);
	    		deadCharacter.removeAll(safeCharacter);
	    	
	    }
	    // rimuovi i deadCharacter anche dal gioco
	    // controlla che ci siano ancora umani, altrimenti hanno vinto gli alieni
	    }
	}	
		
	public boolean checkAttack(){
			if(gameState.getTurn().getTurnState().equals(TurnState.HASMOVED/* && attributo canattack a true*/))
			 return true;
			 
			 return false;
			 
			
		}
		
		
	}
	


