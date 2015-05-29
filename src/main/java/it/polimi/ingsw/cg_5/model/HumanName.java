package it.polimi.ingsw.cg_5.model;

public enum HumanName {
	THE_CAPTAIN("Ennio Maria Dominoni (Captain)"), THE_PILOT("Cabal(pilot)") , THE_PSYCHOLOGIST("Silvano Porpora (psychologist)"),
	THE_SOLDIER("Tuccio Brendon (soldier)");
	
	private final String HumanName;
	
	private HumanName(String name){
		this.HumanName=name;
	}

	public String getHumanName() {
		return HumanName;
	}
	
	
}