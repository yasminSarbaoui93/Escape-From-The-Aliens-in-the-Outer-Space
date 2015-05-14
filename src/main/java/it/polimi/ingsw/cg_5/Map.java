package it.polimi.ingsw.cg_5;

import java.util.HashMap;

public class Map {

	private HashMap <String, Sector> map = new HashMap <String,Sector>();
	
	public void addSector(String name, Sector sector){
		map.put(name, sector);
	}
	
	public Sector takeSector(String name){
		return map.get(name);
	}
}
