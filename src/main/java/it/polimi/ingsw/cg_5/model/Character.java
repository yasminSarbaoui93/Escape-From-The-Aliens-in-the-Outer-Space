package it.polimi.ingsw.cg_5.model;

//umano emula le azioni dell  alieno quando usa la carta attacck e adrenaline per cui il metodo equivarrà per entrambi
//Le  carte sedativo ,spotlight,defence corrisponderanno a metodi solo per l'umano.
//occorrono costruttori diversi per alieno e umano perche hanno valori(maxmove canAttack) diversi al  momento  della  creazione--
public abstract class Character {
	protected int maxMove;
	protected Sector currentSector;
	protected boolean canAttack=false;
	protected String name;
	//probabilmente in character dovremmo inserire un attributo player, perchè ci serve per eliminarlo dal gioco
	// quando il character viene ucciso
	
	//public Character(String name){
		
		//this.name=name; 
	//}
	
	public Sector getCurrentSector(){
		return currentSector;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setCurrentSector(Sector currentSector){
		this.currentSector = currentSector;
	}
	
	public int getMaxMove(){
		return maxMove;
	}
	
	public void setMaxMove(int maxMove){
		this.maxMove = maxMove;
	}
	
	public boolean isCanAttack(){
		return canAttack;
	}
	
	public void setCanAttack(boolean canAttack){
		this.canAttack = canAttack;
	}
		
	
}
