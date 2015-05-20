package it.polimi.ingsw.cg_5.model;

public class SafeSector extends Sector {
	public SafeSector(String name){
		super(name);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+ "Safe";
	}
	/*public boolean isSafeSector(Object o){
		prova.map.get("A03").getClass() == SafeSector.class
	}
	*/
}
