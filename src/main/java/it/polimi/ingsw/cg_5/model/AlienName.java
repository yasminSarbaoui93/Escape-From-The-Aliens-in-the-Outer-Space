package it.polimi.ingsw.cg_5.model;

public enum AlienName {
	PIERO_CECCARELLA("Piero Ceccarella (first alien)"), VITTORIO_MARTANA("Vittorio Martana (second alien)") , MARIA_GALBANI("Maria Galbani (Third Alien)"),
	PAOLO_LANDON("Paolo Landon (fourth alien)");
	
	private final String alienName;
	
	private AlienName(String name){
		this.alienName=name;
	}

	public String getAlienName() {
		return alienName;
	}
	
	
}


