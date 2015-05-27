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
		
		//Added C03 because the EscapeHatchSector can be even crossed to reach another sector
		comparisonSectors.add(map1.map.get("A04"));
		comparisonSectors.add(map1.map.get("B03"));
		comparisonSectors.add(map1.map.get("C02"));
		comparisonSectors.add(map1.map.get("C01"));
		comparisonSectors.add(map1.map.get("C03"));		
	
		assertEquals(reachableSectors = map1.takeSector("A02").getReachableSectors(2, map1.takeSector("A02")), comparisonSectors);
		System.out.println("Reachable sectors for maxMove = 2 are: " +reachableSectors);
		
		comparisonSectors.add(map1.map.get("D02"));
		comparisonSectors.add(map1.map.get("C04"));
		comparisonSectors.add(map1.map.get("A05"));
		comparisonSectors.add(map1.map.get("B04"));
		comparisonSectors.add(map1.map.get("D03"));

		
		assertEquals(reachableSectors = map1.takeSector("A02").getReachableSectors(3, map1.takeSector("A02")), comparisonSectors);
		System.out.println("Reachable sectors for maxMove = 3 are: "+reachableSectors);

	
		//testing for another sector
		map1.map.get("F02").bordersPrint();
		HashSet<Sector> comparisonSector2 = new HashSet<Sector>();
		HashSet<Sector> reachableSector2 = new HashSet<Sector>();
		
		comparisonSector2.add(map1.map.get("F01"));
		comparisonSector2.add(map1.map.get("G02"));
		comparisonSector2.add(map1.map.get("G03"));
		comparisonSector2.add(map1.map.get("F03"));
		comparisonSector2.add(map1.map.get("E03"));
		comparisonSector2.add(map1.map.get("E02"));
		
		assertEquals(reachableSector2 = map1.takeSector("F02").getReachableSectors(1, map1.takeSector("F02")), comparisonSector2 );
		System.out.println("Reachable sectors for maxMove = 1 are: "+reachableSector2);
		
		comparisonSector2.add(map1.map.get("G01"));
		comparisonSector2.add(map1.map.get("H01"));
		comparisonSector2.add(map1.map.get("H02"));
		comparisonSector2.add(map1.map.get("H03"));
		comparisonSector2.add(map1.map.get("G04"));
		comparisonSector2.add(map1.map.get("F04"));
		comparisonSector2.add(map1.map.get("E04"));
		comparisonSector2.add(map1.map.get("D03"));
		comparisonSector2.add(map1.map.get("D02"));
		
		assertEquals(reachableSector2 = map1.takeSector("F02").getReachableSectors(2, map1.takeSector("F02")), comparisonSector2 );
		System.out.println("Reachable sectors for maxMove = 2 are: "+reachableSector2);
		
		
		comparisonSector2.add(map1.map.get("I01"));
		comparisonSector2.add(map1.map.get("I02"));
		comparisonSector2.add(map1.map.get("I03"));
		comparisonSector2.add(map1.map.get("I04"));
		comparisonSector2.add(map1.map.get("H04"));
		comparisonSector2.add(map1.map.get("G05"));
		comparisonSector2.add(map1.map.get("F05"));
		comparisonSector2.add(map1.map.get("E05"));
		comparisonSector2.add(map1.map.get("C04"));
		comparisonSector2.add(map1.map.get("C03"));
		comparisonSector2.add(map1.map.get("C02"));
		
		assertEquals(reachableSector2 = map1.takeSector("F02").getReachableSectors(3, map1.takeSector("F02")), comparisonSector2 );
		System.out.println("Reachable sectors for maxMove = 3 are: "+reachableSector2);
		
		}
	
	

	
	
	public void testFile(){
		
	}

}
