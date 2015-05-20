package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.Iterator;

public class Sector {
	
private HashSet <Sector> bordersList= new HashSet<Sector>();
//private ArrayList <Character> playerList = new ArrayList <Character> ();
private final String sectorName;

public Sector(String sectorName){
	this.sectorName=sectorName;
}

public String getSectorName() {
	return sectorName;
}
public HashSet <Sector> getReachableSectors(int i){
	HashSet <Sector> listaTuttiConfini = new HashSet <Sector> ();
	HashSet <Sector> supporto = new HashSet <Sector> ();
	
	listaTuttiConfini.addAll(bordersList);
		for(int j=1; j<i ; j++){	
			supporto.addAll(listaTuttiConfini);
		Iterator <Sector> it = supporto.iterator();
			while (it.hasNext()) {
			listaTuttiConfini.addAll(it.next().getReachableSectors(1));		
			}
		}
	return listaTuttiConfini;	
	
=======


public  class Sector {
	
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
>>>>>>> refs/heads/master
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
