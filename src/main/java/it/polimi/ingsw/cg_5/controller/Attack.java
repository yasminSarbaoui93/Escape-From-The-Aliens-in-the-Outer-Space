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
		// SE IL SECTORE CONTIENE SOLO IL CURRENT PLAYERL'ATTACCO ANDRA A VUOTO
		if (gameState.getCurrentCharacter().getCurrentSector().getCharacterList().size()==1){
			//il giocatore ha attaccato in A00, l'attacco non è andato a buon fine
			System.out.println("ops non c'è nessuno");
		}
		else {
		ArrayList <Character> characterToKill = new ArrayList <Character> ();
		System.out.println(gameState.getCurrentCharacter().getCurrentSector().getCharacterList());
		characterToKill.addAll(gameState.getCurrentCharacter().getCurrentSector().getCharacterList());
		System.out.println(characterToKill);
		//essendo il player che attacck nella sua posizione lo dobbiamo rimuovere
		characterToKill.remove(gameState.getCurrentCharacter());
		ArrayList<Character> safeCharacter=new ArrayList<Character>();
		
		ItemCard defenceCard = null;
		
		for(Character character: characterToKill){
	    	for(ItemCard itemCard:character.getItemPlayerCard()){
	    		if(itemCard.getItemCardType()==ItemCardType.DEFENCE){
	    			safeCharacter.add(character);
	    			defenceCard=itemCard;
	    		}
	    	}
	    	
	    	}
		
		// la lista dei giocatori non e' vuota allora vuol dire che bisognera levare il player con safe
    	if(!safeCharacter.isEmpty()){
    		safeCharacter.get(0).getItemPlayerCard().remove(defenceCard);
    		characterToKill.removeAll(safeCharacter);
    	gameState.getItemDeck().getUsedItemDeck().add(defenceCard);
	    	}
    	System.out.println(characterToKill);
    	//rimuove dalla lista dei giocatori i player attaccati senza la defence card
		gameState.getCharacterList().removeAll(characterToKill);
		gameState.getCurrentCharacter().getCurrentSector().getCharacterList().removeAll(characterToKill);
	    	System.out.println("\ni player rimasti nel settore sono"+gameState.getCurrentCharacter().getCurrentSector().getCharacterList());
	    	/*System.out.println(gameState.getMap().takeSector(gameState
	    			.getCurrentCharacter().getCurrentSector().getSectorName()).getCharacterList());*/
	    	
	    	
	    	
	    }
	    	
	    
	    }
		
		
	public boolean checkAttack(){
			//if(gameState.getCurrentCharacter().getClass()==Human.class){
			if(gameState.getTurn().getTurnState().equals(TurnState.HASMOVED) && gameState.getCurrentCharacter().isCanAttack())
			 return true;
			//}
			/*if(gameState.getCurrentCharacter().getClass()==Alien.class){
				if(gameState.getTurn().getTurnState().equals(TurnState.HASMOVED))
					return true;
			}
			*/
			return false;
	
			
		}
		
		
	}
	


