package it.polimi.ingsw.cg_5.model;

public class DangerousSector extends Sector {
	public DangerousSector(String name){
		super(name);
	}
	
	public String toString() {
		return super.toString()+ "(D)";
	}
}
