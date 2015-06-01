package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameStateTest {

	@Test
	public void startingNewGameWithGalileiTest() {
		
		//Players Id created by the controller
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		//Pretending to have 5 players that joined the game with Galilei map
		for (int i=0 ; i<5; i++){
			playersID.add(i);
		}
		
		//New GameState with Galilei map and 5 players identified by their IDs
		GameState matchGalilei1= new GameState (playersID, "Galilei");
		System.out.println("The map used is " + matchGalilei1.getMap().getMapName());
		Iterator <Character> iterator = matchGalilei1.getCharacterList().iterator();
		
		ArrayList <Character> listOfCharacters = matchGalilei1.getCharacterList();
		System.out.println(listOfCharacters+"\n");
		
		Character character;
		while (iterator.hasNext()) {
			character=iterator.next();			
			System.out.println("The character " +character.getName() + "  of the player with ID-" +character.getPlayerID()+
					" is on the sector " + character.getCurrentSector().toString());
			
			System.out.println(character.getName()+"can move to the sectors: "+matchGalilei1.reachableSectorsOfTheCurrentCharacter(character)+"\n");
					
		} 
		
		
		System.out.println("STARTING THE ROUND NUMBER " + matchGalilei1.getRound()+"\n");
		
		System.out.println("It's the turn of the player with ID-"+matchGalilei1.getCurrentCharacter().getPlayerID());
		matchGalilei1.currentCaracterDrawsItemCard();
		matchGalilei1.currentCaracterDrawsItemCard();
		matchGalilei1.currentCaracterDrawsItemCard();
		System.out.println(matchGalilei1.getCurrentCharacter()+" and my item cards are\n"+matchGalilei1.getCurrentCharacter().getItemPlayerCard());
		
		//Change turn of the player
		matchGalilei1.goToNextCharacter();;
		System.out.println("\nNow it's the turn of the player with ID-"+matchGalilei1.getCurrentCharacter().getPlayerID());
		System.out.println(matchGalilei1.getCurrentCharacter());
		
		matchGalilei1.currentCaracterDrawsItemCard();
		matchGalilei1.currentCaracterDrawsItemCard();
		System.out.println(matchGalilei1.getCurrentCharacter().getItemPlayerCard());
		
		//Change turn of the player
		matchGalilei1.goToNextCharacter();
		System.out.println("\nNow it's the turn of the player with ID-"+matchGalilei1.getCurrentCharacter().getPlayerID());
		System.out.println(matchGalilei1.getCurrentCharacter());
		matchGalilei1.currentCaracterDrawsItemCard();
		matchGalilei1.currentCaracterDrawsItemCard();
		matchGalilei1.currentCaracterDrawsItemCard();
		matchGalilei1.currentCaracterDrawsItemCard();
		System.out.println(matchGalilei1.getCurrentCharacter().getItemPlayerCard());

		System.out.println("\n"+matchGalilei1.getCharacterList().get(2)+" and I have just been ATTACKED");
		matchGalilei1.removeCharacter(matchGalilei1.getCharacterList().remove(2));
		
		//After killing a character, the list has to get smaller and the character's position shiftet left
		assertEquals(4, matchGalilei1.getCharacterList().size());
		
		
		for(int i=0; i<10; i++){
		//Change turn of the player
		matchGalilei1.goToNextCharacter();
		System.out.println("\nNow it's the turn of the player with ID-"+matchGalilei1.getCurrentCharacter().getPlayerID());
		System.out.println(matchGalilei1.getCurrentCharacter());
		}
		
		for(int i=0; i<183; i++){
			matchGalilei1.goToNextCharacter();
		}

	}
	




}
