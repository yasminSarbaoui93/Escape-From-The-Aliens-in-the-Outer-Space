package it.polimi.ingsw.cg_5.model;

public class Human extends Character {
	 
		
	public Human(String name, int playerID){
		super(name,playerID);
		maxMove = 5;
		canAttack =false;
	//	this.currentSector = humanStart;
	}
	
	

	@Override
	public String toString() {
			
		return "I'm the Human "+name;	
	}
	

	
	
	

}
