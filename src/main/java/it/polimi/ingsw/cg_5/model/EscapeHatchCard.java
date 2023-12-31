package it.polimi.ingsw.cg_5.model;

public  class EscapeHatchCard implements Card {

	private final EscapeHatchType escapeHatchType;

	public EscapeHatchCard(EscapeHatchType escapeHatchType) {
		
		this.escapeHatchType = escapeHatchType;
	}
	

	public boolean isRed(){
		return this.escapeHatchType.equals(EscapeHatchType.RED_SHALLOP);
	}
	
	public boolean isGreen(){
		return this.escapeHatchType.equals(EscapeHatchType.GREEN_SHALLOP);
	}

	@Override
	public String toString() {
		return "EscapeHatchCard (" + escapeHatchType + ")";
	}


	public EscapeHatchType getEscapeHatchType() {
		return escapeHatchType;
	}

}
