package it.polimi.ingsw.cg_5.model;

public  class EscapeHatchCard implements Card {
	
	// tipo
	private final EscapeHatchType escapeHatchType;
	
// costruttore
	public EscapeHatchCard(EscapeHatchType escapeHatchType) {
		
		this.escapeHatchType = escapeHatchType;
	}
<<<<<<< HEAD
=======




	@Override
	public void cardEffect() {
		// TODO Auto-generated method stub
		
	}
>>>>>>> refs/heads/master
	


	@Override
	public String toString() {
		return "EscapeHatchCard (" + escapeHatchType + ")";
	}




	public EscapeHatchType getEscapeHatchType() {
		return escapeHatchType;
	}

	
	
	
	

}
