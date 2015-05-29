package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
import java.util.Iterator;

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
		Iterator <Character> iterator = matchGalilei1.getCharacterList().iterator();
		
		ArrayList <Character> listOfCharacters = matchGalilei1.getCharacterList();
		System.out.println(listOfCharacters+"\n");
		
		Character character;
		while (iterator.hasNext()) {
			character=iterator.next();			
			System.out.println("The character " +character.getName() + "  of the player with ID-" +character.getPlayerID()+
					" is on the sector " + character.getCurrentSector().toString());
			
			System.out.println(character.getName()+"can move to the sectors: " +
					matchGalilei1.getMap().takeSector(character.getCurrentSector()
			.getSectorName()).getReachableSectors(character.getMaxMove(), character.getCurrentSector()) + "\n" );
					
		} 
		
		
		
		matchGalilei1.getCurrentCharacter().getItemPlayerCard().add(matchGalilei1.getItemDeck().removeCard());
		matchGalilei1.getCurrentCharacter().getItemPlayerCard().add(matchGalilei1.getItemDeck().removeCard());
		matchGalilei1.getCurrentCharacter().getItemPlayerCard().add(matchGalilei1.getItemDeck().removeCard());
		System.out.println("The item cards of the character "+matchGalilei1.getCurrentCharacter()+" are "+matchGalilei1.getCurrentCharacter().getItemPlayerCard());
		
	//	turnMatchGalilei.increasePlayerTurn();
		

		matchGalilei1.goToNextCharacter();;

		
	//	Character oldCurrentCharacter = matchGalilei1.getCurrentCharacter();
		//matchGalilei1.getCharacterList().remove(oldCurrentCharacter);
	//	matchGalilei1.setCurrentCharacter(matchGalilei1.getCharacterList().get(0));
		//matchGalilei1.getCharacterList().add(oldCurrentCharacter);
		
		System.out.println(matchGalilei1.getCurrentCharacter());
		
		matchGalilei1.getCurrentCharacter().getItemPlayerCard().add(matchGalilei1.getItemDeck().removeCard());
		matchGalilei1.getCurrentCharacter().getItemPlayerCard().add(matchGalilei1.getItemDeck().removeCard());
		System.out.println(matchGalilei1.getCurrentCharacter().getItemPlayerCard());

	}



}
