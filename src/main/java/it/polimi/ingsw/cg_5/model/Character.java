package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;

//umano emula le azioni dell  alieno quando usa la carta attack e adrenaline per cui il metodo equivarrà per entrambi
//Le  carte sedativo ,spotlight,defence corrisponderanno a metodi solo per l'umano.
//occorrono costruttori diversi per alieno e umano perche hanno valori(maxmove canAttack) diversi al  momento  della  creazione--
public abstract class Character {
	protected int maxMove;
	protected Sector currentSector;
	protected String name;
	private final int playerID;
	private ArrayList <ItemCard> itemPlayerCard=new ArrayList <ItemCard> ();
	//probabilmente in character dovremmo inserire un attributo player, perchè ci serve per eliminarlo dal gioco
	// quando il character viene ucciso
	
	public Character(String name, int playerID){
		this.name=name; 
		this.playerID=playerID;
	}
	public ArrayList<ItemCard> getItemPlayerCard() {
		return itemPlayerCard;
	}
	
	
	public int getPlayerID() {
		return playerID;
	}


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
	@Override
	public String toString() {
		return "Character [name=" + name + ", playerID=" + playerID + "]";
	}
	

		
	
}
