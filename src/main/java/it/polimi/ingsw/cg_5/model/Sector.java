package it.polimi.ingsw.cg_5.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class Sector {
	
	private HashSet <Sector> bordersList= new HashSet<Sector>();
	private ArrayList <Character> characterList = new ArrayList <Character> ();
	private final String sectorName;

	public Sector(String sectorName){
		this.sectorName=sectorName;
	}

	public String getSectorName() {
		return sectorName;
	}

	public ArrayList<Character> getCharacterList() {
		return characterList;
	}

	public void setCharacterList(ArrayList<Character> characterList) {
		this.characterList = characterList;
	}

/**
 *Method that creates a list of all the reachable sectors starting from the current one; it depends on the max movement that the current player can do.
 *Given the starting sector as a parameter so that it will be removed from the list of reachable sectors. In this method there's even a control that removes the Alien's and Human's starting sector from the reachable sector
 *since they cannot be crossed by any character after the first movement.
 * @param maxMove
 * @param startSector
 * @return a list of all the reachable sectors
 */

	public HashSet <Sector> getReachableSectors(int maxMove, Sector startSector){ 
		HashSet <Sector> listOfAllConfines = new HashSet <Sector> ();
		HashSet <Sector> support = new HashSet <Sector> ();
		listOfAllConfines.addAll(bordersList);
	
			for(int j=1; j<maxMove ; j++){	
				support.addAll(listOfAllConfines);
				Iterator <Sector> it = support.iterator();
				while (it.hasNext()) {
				
					Sector nextSector = it.next();				
				
						listOfAllConfines.addAll(nextSector.getReachableSectors(1, startSector));
		
						nextSector = it.next();		
				}
				listOfAllConfines.remove(startSector);		
			}
		
			return listOfAllConfines;	
		}
	@Override
	public String toString() {
		return  sectorName +" ";
	}
	public void addBorder(Sector border) {
		this.bordersList.add(border);	
	}
	public void bordersPrint(){
		System.out.println("The sector ["+ this.getSectorName()+"] confines with :" + bordersList);
	}


}
