package it.polimi.ingsw.cg_5.model;

public class EscapeSector extends Sector {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isAvailable;
	public EscapeSector(String name){
		super(name);
		isAvailable=true;
	}
	
	//getter to verify if the sector has never been used yet
	public boolean isAvailable() {
		return isAvailable;
	}
	
	//setter to change the status of this sector after a human got saved
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
public String toString() {
		return this.getSectorName();
		
	}
	
	
}
