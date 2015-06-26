package it.polimi.ingsw.cg_5.model;

public class DangerousSector extends Sector {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DangerousSector(String name){
		super(name);
	}
	
	public String toString() {
		return super.toString()+ "(D)";
	}
}
