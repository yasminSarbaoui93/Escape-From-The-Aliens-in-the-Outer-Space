package it.polimi.ingsw.cg_5.model;

public class Human extends Character {
	 
		
	public Human(String name, int playerID){
		super(name,playerID);
		maxMove = 20;
		canAttack =false;
	//	this.currentSector = humanStart;
	}
	
	
	public void setHumanBack(){
		maxMove=1;
		canAttack=false;
	}
	@Override
	public String toString() {
			
		return name;	
	}
	

	
	
	

}
