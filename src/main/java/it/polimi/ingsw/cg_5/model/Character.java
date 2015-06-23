package it.polimi.ingsw.cg_5.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.lang.model.type.UnionType;



public abstract class Character implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int maxMove;
	protected Sector currentSector;
	protected final String name;
	private final Integer playerID;
	protected boolean canAttack;
	private ArrayList <ItemCard> itemPlayerCard=new ArrayList <ItemCard> ();
	
	public Character(String name, Integer playerID){
		this.name=name; 
		this.playerID=playerID;
	}
	public ArrayList<ItemCard> getItemPlayerCard() {
		return itemPlayerCard;
	}
	
	
	public Integer getPlayerID() {
		return playerID;
	}


	public Sector getCurrentSector(){
		return currentSector;
	}
	
	
	public String getName() {
		return name;
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
	
	public boolean isCanAttack() {
		return canAttack;
	}

	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}
	
}
