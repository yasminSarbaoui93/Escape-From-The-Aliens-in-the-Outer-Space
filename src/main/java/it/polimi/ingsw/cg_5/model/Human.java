package it.polimi.ingsw.cg_5.model;

public class Human extends Character {

		
	public Human(String name, int playerID){
		super(name,playerID);
		maxMove = 1;
	//	this.currentSector = humanStart;
	}
	
	private String humanRole;
	
	public String associateHumanName(){
		if(name.equals("Silvano Porpora"))
				humanRole = "Psychologist";
		if(name.equals("Piri"))
			humanRole = "Soldier";
		if(name.equals("Ennio Maria Dominoni"))
			humanRole = "Captain";
		if(name.equals("Cabal"))
			humanRole = "Pilot";
		return humanRole;
	}

	@Override
	public String toString() {
			
		return "I'm the Human "+name+" and i am the " +associateHumanName();	
	}
	

}
