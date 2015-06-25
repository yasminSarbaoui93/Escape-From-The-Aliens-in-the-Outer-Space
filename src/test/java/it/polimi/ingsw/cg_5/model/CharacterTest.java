package it.polimi.ingsw.cg_5.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class CharacterTest {

	@Test
	public void CharacterCreationTest() {
		
		ArrayList <Character> characters = new ArrayList<Character>();
		Human captain = new Human("Ennio Maria Dominoni", 0000);
		Human pilot = new Human("Cabal", 0001);
		Human psychologist = new Human("Silvano Porpora", 0002);
		Human soldier = new Human("Piri", 0003);
		Alien firstAlien = new Alien("Piero Ceccarella", 0004);
		Alien secondAlien = new Alien("Vittorio Martana", 0005);
		Alien thirdAlien = new Alien("Maria Galbani", 0006);
		Alien fourthAlien = new Alien("Paolo Landon", 0007);
		
		characters.add(captain);
		characters.add(pilot);
		characters.add(psychologist);
		characters.add(soldier);
		characters.add(firstAlien);
		characters.add(secondAlien);
		characters.add(thirdAlien);
		characters.add(fourthAlien);
		
		Iterator <Character> iteratore = characters.iterator();
		Character character;
		while(iteratore.hasNext()){
			character = iteratore.next();
			System.out.println(character);
		}
		
		Map galileiMap = new Map("GALILEI");
		System.out.println("\n\n");

		
		captain.setCurrentSector(galileiMap.map.get("HUMAN_START"));
		pilot.setCurrentSector(galileiMap.map.get("HUMAN_START"));
		psychologist.setCurrentSector(galileiMap.map.get("HUMAN_START"));
		soldier.setCurrentSector(galileiMap.map.get("HUMAN_START"));
		
		firstAlien.setCurrentSector(galileiMap.map.get("ALIEN_START"));
		secondAlien.setCurrentSector(galileiMap.map.get("ALIEN_START"));
		thirdAlien.setCurrentSector(galileiMap.map.get("ALIEN_START"));
		fourthAlien.setCurrentSector(galileiMap.map.get("ALIEN_START"));
		
		Sector currentSector = thirdAlien.getCurrentSector();
		System.out.println("The thirdAlien's current sector is: "+currentSector);
		
		secondAlien.setMaxMove(3);
		System.out.println("The second alien's max move is: "+secondAlien.getMaxMove());
		System.out.println("The first alien's max move is: "+firstAlien.getMaxMove());
		
		soldier.setCurrentSector(galileiMap.map.get("M03"));
		Sector soldierSector;
		assertEquals(galileiMap.map.get("M03"), soldierSector = soldier.getCurrentSector());
		System.out.println("The soldier's current sector is: "+soldierSector);
		
		// test setBack
		soldier.setCanAttack(true);
		soldier.setMaxMove(100);
		soldier.setHumanBack();
		assertEquals(1, soldier.getMaxMove());
		assertEquals(false,soldier.isCanAttack());
		
		System.out.println("The psychologist's name is: "+psychologist.getName());
	}

}
