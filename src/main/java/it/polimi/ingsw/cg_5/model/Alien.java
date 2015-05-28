package it.polimi.ingsw.cg_5.model;

public class Alien extends Character {
	

	public Alien(String name,int playerID){
		super(name,playerID);
		maxMove = 2;
	}
	
	private String alienRole;
	
	public String associateAlienName(){
		if(name.equals("Piero Ceccarella"))
				alienRole = "first alien";
		if(name.equals("Vittorio Martana"))
			alienRole = "second alien";
		if(name.equals("Maria Galbani"))
			alienRole = "third alien";
		if(name.equals("Paolo Landon"))
			alienRole = "fourth alien";
		return alienRole;
	}

	@Override
	public String toString() {
			
		return "I'm "+name+" and i am the " +associateAlienName();	
	}

	

}
