package it.polimi.ingsw.cg_5.model;

public class Alien extends Character {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Alien(String name,int playerID){
		
		super(name,playerID);
		maxMove =2; 
		canAttack =true;
	}
	

	@Override
	public String toString() {
			
		return name;	
	}

	

}
