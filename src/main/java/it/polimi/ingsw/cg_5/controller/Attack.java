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
	
	/*	if (gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector().getCharacterList().isEmpty()){
			//il giocatore ha attaccato in A00, l'attacco non è andato a buon fine
		}
		else 
		ArrayList <Character> deadCharacter = new ArrayList <Character> ();
		deadCharacter.addAll(gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector().getCharacterList());
	    gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector().getCharacterList().clear();
	    // rimuovi i deadCharacter anche dal gioco
	    // controlla che ci siano ancora umani, altrimenti hanno vinto gli alieni
	    
		}
		
		/*public boolean checkAttack(){
			//if(gamestate.getTurn.getTurnState.equals(turnState.HASMOVED))
			 * return true;
			 * 
			 *return false;
			 */
			
		}
		
		
	}
	


