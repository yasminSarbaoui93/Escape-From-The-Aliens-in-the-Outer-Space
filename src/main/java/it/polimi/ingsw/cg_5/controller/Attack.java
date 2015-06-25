package it.polimi.ingsw.cg_5.controller;
import java.util.ArrayList;

import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;


public class Attack extends Action {
	private ArrayList <Character> characterToKill = new ArrayList <Character> ();
	private Match match;
	public ArrayList<Integer> getPlayerToKill() {
		ArrayList <Integer> playerIdToKill = new ArrayList <Integer> () ;
		for(Character character : characterToKill){
			playerIdToKill.add(character.getPlayerID());
		}
		return playerIdToKill;
	}
	private ArrayList<Character> safeCharacter=new ArrayList<Character>();//questo safecharacter è un eventuale player

	public ArrayList<Character> getSafeCharacter() {
		return safeCharacter;
	}

	public Attack (GameState gameState,Match match){
	super(gameState);
	this.match=match;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.cg_5.controller.Action#execute()
	 * The attack can be done after the character moved onto the new current sector. So if in the current sector there's only the current character, the attack will be done but not succesfully.
	 */
	@Override
	public void execute() {

		characterToKill.addAll(gameState.getCurrentCharacter().getCurrentSector().getCharacterList());
		characterToKill.remove(gameState.getCurrentCharacter());
		
		ItemCard defenceCard = null;
		for(Character character: characterToKill){
	    	for(ItemCard itemCard:character.getItemPlayerCard()){
	    		if(itemCard.getItemCardType()==ItemCardType.DEFENCE){
	    			safeCharacter.add(character);
	    			defenceCard=itemCard;
	    		}
	    	}
	    	
	    }

		if(!safeCharacter.isEmpty()){
    		safeCharacter.get(0).getItemPlayerCard().remove(defenceCard);

    		characterToKill.removeAll(safeCharacter);
    	for(Character character : safeCharacter){
    		int i=gameState.getCharacterList().indexOf(character);
    		gameState.getCharacterList().get(i).getItemPlayerCard().remove(defenceCard);
    	}
    	gameState.getItemDeck().getUsedItemDeck().add(defenceCard);//scarto la carta difesa
	    	}
		if(gameState.getCurrentCharacter().getClass()==Alien.class){
			for(Character character: characterToKill){
				if(character.getClass()==Human.class)
					gameState.getCurrentCharacter().setMaxMove(3); 
			}
		}
		for(Character attackedCharacter : characterToKill){
			gameState.removeCharacter(attackedCharacter);
			gameState.getLosers().add(attackedCharacter);
			gameState.getCurrentCharacter().getCurrentSector().getCharacterList().remove(attackedCharacter);
		}
   	
	    gameState.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);	
	    
	    if(match.isGameOver()){
			System.out.println("aaa");
			match.setMatchState(MatchState.ENDED);
			System.out.println(match.getGameState());
			}
	    
	    }
		
		
	/**This method checks if the character that is willing to attack can really do it. If the character is a human, it can attack only if he has the item card to do it,
	 * while if the character is an alien, he can always attack if he already moved to the sector where he wants to attack.
	 * @return True if the character can attack; false if the character is not allowd to attack.
	 */
	@Override
	public boolean checkAction(){

			//if(gameState.getCurrentCharacter().getClass()==Human.class){
			if(gameState.getTurn().getTurnState().equals(TurnState.HASMOVED) && gameState.getCurrentCharacter().isCanAttack())
				return true;
		
			return false;
	}
		
		
}
	


