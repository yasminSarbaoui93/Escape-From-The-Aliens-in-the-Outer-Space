package it.polimi.ingsw.cg_5.model;

public enum HumanName {
	THE_CAPTAIN("THE CAPTAIN"), THE_PILOT("THE PILOT") , THE_PSYCHOLOGIST("THE PSYCHOLOGIST"),
	THE_SOLDIER("THE SOLDIER");
	
	private final String HumanName;
	
	private HumanName(String name){
		this.HumanName=name;
	}

	public String getHumanName() {
		return HumanName;
	}
	
	
}