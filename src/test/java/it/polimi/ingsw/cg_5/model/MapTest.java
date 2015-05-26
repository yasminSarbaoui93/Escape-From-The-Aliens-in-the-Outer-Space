package it.polimi.ingsw.cg_5.model;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class MapTest {

	@Test
	public void creationMapTest() {
		
		//Creation of the map
		Map map1= new Map("GALILEI");
		map1.printMap();
		
		//print the borders of sector A02
		map1.map.get("A02").bordersPrint();
		
		HashSet <Sector> reachableSectors = new HashSet <Sector> ();
		
		//Set to compare the result of the method with the real borders and moves
		HashSet <Sector> comparisonSectors = new HashSet <Sector>();
		comparisonSectors.add(map1.map.get("B01"));
		comparisonSectors.add(map1.map.get("1"));
		comparisonSectors.add(map1.map.get("A03"));
		
		//The reachable sectors if the maxMove is 1, are equals to the borders of the same sector
		
		assertEquals(reachableSectors = map1.takeSector("A02").getReachableSectors(1, map1.takeSector("A02")), comparisonSectors);
		System.out.println("Reachable sectors for maxMove = 1 are: "+reachableSectors);
		
		comparisonSectors.add(map1.map.get("A04"));
		comparisonSectors.add(map1.map.get("B03"));
		comparisonSectors.add(map1.map.get("C02"));
		comparisonSectors.add(map1.map.get("C01"));
		
	
		assertEquals(reachableSectors = map1.takeSector("A02").getReachableSectors(2, map1.takeSector("A02")), comparisonSectors);
		System.out.println("Reachable sectors for maxMove = 2 are: " +reachableSectors);
		
		comparisonSectors.add(map1.map.get("D02"));
		comparisonSectors.add(map1.map.get("C04"));
		comparisonSectors.add(map1.map.get("A05"));
		comparisonSectors.add(map1.map.get("B04"));
		comparisonSectors.add(map1.map.get("C03"));
		
		assertEquals(reachableSectors = map1.takeSector("A02").getReachableSectors(3, map1.takeSector("A02")), comparisonSectors);
		System.out.println("Reachable sectors for maxMove = 3 are: "+reachableSectors);
	}
	
	public void testFile(){
		
	}

}
