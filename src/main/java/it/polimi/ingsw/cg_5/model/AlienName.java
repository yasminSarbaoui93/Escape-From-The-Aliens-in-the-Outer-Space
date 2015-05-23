package it.polimi.ingsw.cg_5.model;

public enum AlienName {
	PIERO_CECCARELLA("PIERO CECCARELLA"), VITTORIO_MARTANA("VITTORIO MARTANA") , MARIA_GALBANI("MARIA GALBANI"),
	PAOLO_LANDON("PAOLO LANDON");
	
	private final String alienName;
	
	private AlienName(String name){
		this.alienName=name;
	}

	public String getAlienName() {
		return alienName;
	}
	
	
}


