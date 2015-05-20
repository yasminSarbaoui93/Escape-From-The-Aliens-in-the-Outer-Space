package it.polimi.ingsw.cg_5.model;

public class Human extends Character {

	
	public Human(String name) {
		canAttack=false;
		maxMove=1;
		this.name=name;
		
		//this.currentSector=humanStart;// settore inizio umani
	}

}
