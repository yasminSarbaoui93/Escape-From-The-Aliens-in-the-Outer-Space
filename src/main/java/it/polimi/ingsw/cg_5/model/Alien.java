package it.polimi.ingsw.cg_5.model;

public class Alien extends Character {
	

	public Alien(String name,int playerID){
		
		super(name,playerID);
		maxMove = 10;
		canAttack =true;
	}
	

	@Override
	public String toString() {
			
		return "I'm "+name;	
	}

	

}
