package it.polimi.ingsw.cg_5.model;

public class SafeSector extends Sector {
	public SafeSector(String name){
		super(name);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"(S)";
	}

	public boolean isSafeSector(Sector a){
		return a.getClass() == SafeSector.class;
	}
	

}
