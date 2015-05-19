package it.polimi.ingsw.cg_5;

import java.util.ArrayList;

public  class Sector {
	//kjjlk
private ArrayList <Sector> bordersList= new ArrayList <Sector> ();
//private ArrayList <Character> playerList = new ArrayList <Character> ();
private final String sectorName;

public Sector(String sectorName){
	this.sectorName=sectorName;
}

public String getSectorName() {
	return sectorName;
}
public ArrayList <Sector> getReachableSectors(){
	return this.bordersList;
}
@Override
public String toString() {
	return  sectorName +" ";
}
public void addBorder(Sector border) {
	this.bordersList.add(border);	
}
public void bordersPrint(){
	System.out.println("Il settore confina con :" + bordersList);
}

}
