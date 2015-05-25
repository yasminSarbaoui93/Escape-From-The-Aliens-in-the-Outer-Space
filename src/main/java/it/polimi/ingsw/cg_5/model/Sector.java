package it.polimi.ingsw.cg_5.model;


import java.util.HashSet;
import java.util.Iterator;

public abstract class Sector {
	
private HashSet <Sector> bordersList= new HashSet<Sector>();
//private ArrayList <Character> characterList = new ArrayList <Character> ();
private final String sectorName;

public Sector(String sectorName){
	this.sectorName=sectorName;
}

public String getSectorName() {
	return sectorName;
}


/**
 *Method that creates a list of all the reachable sectors starting from the current one; it depends on the max movement that the current player can do.
 *Given the starting sector as a parameter so that it will be removed from the list of reachable sectors
 * @param maxMove
 * @param startSector
 * @return a list of all the reachable sectors
 */
public HashSet <Sector> getReachableSectors(int maxMove, Sector startSector){ 
	HashSet <Sector> listaTuttiConfini = new HashSet <Sector> ();
	HashSet <Sector> support = new HashSet <Sector> ();
	
	listaTuttiConfini.addAll(bordersList);
	
		for(int j=1; j<maxMove ; j++){	
			support.addAll(listaTuttiConfini);
			Iterator <Sector> it = support.iterator();
			while (it.hasNext()) {
			listaTuttiConfini.addAll(it.next().getReachableSectors(1, startSector));
			
			}
			listaTuttiConfini.remove(startSector);
		}
		
	return listaTuttiConfini;	
	

}
@Override
public String toString() {
	return  sectorName +" ";
}
public void addBorder(Sector border) {
	this.bordersList.add(border);	
}
public void bordersPrint(){
	System.out.println("The sector confines with :" + bordersList);
}

}
