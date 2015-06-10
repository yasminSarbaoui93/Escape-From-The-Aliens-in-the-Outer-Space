package it.polimi.ingsw.cg_5.model;

import org.junit.Test;

public class MapTest {

	@Test
	public void creationMapTest() {
		
		
		//Creation of the map
		Map map1= new Map("GALILEI");

		map1.printMap("GALILEI");

		//print the borders of sector A02
		//map1.map.get("F02").bordersPrint();
		

		//Creation of the map
		Map map2= new Map("FERMI");
		map2.printMap("FERMI");
		map2.map.get("M02").bordersPrint();

		

		//Creation of the map
		Map map3= new Map("GALVANI");
		map3.printMap("GALVANI");
		
		//print the borders of sector A02
		map3.map.get("J06").bordersPrint();

		
		//Human and Alien start are not seen as borders since we can't cross them doring the game
		map3.map.get("K06").bordersPrint();
		

		System.out.println("\n\n");
		map1.drawMap();
		map2.drawMap();
		map3.drawMap();
	}

	
	
	
	

	
	
	public void testFile(){
		
	}

}
