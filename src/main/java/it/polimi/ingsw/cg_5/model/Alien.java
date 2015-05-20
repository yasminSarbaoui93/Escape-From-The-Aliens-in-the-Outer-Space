package it.polimi.ingsw.cg_5.model;

public class Alien extends Character {
	

	
	public Alien(String name) { //IL COSTRUTTORE CHIAMA IL SUPERCOSTRUTTORE CON I PARAMETRI DI DEFAULT DELL'ALIENO!
		canAttack=true;
		maxMove=2;
		this.name=name;
		
		 		//this.currentSector=alienStart;// settore inizio alieni
		}

	

}
