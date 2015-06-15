package it.polimi.ingsw.cg_5.connection.playerDTO;

import java.io.Serializable;
import java.util.ArrayList;
import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.Sector;


//Those are all the attributes that will be sent by the server to the client
//in answer to every change that the player does to the game state.
public class PlayerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Character yourCharacter;
	public Character getYourCharacter() {
		return yourCharacter;
	}
	public void setYourCharacter(Character yourCharacter) {
		this.yourCharacter = yourCharacter;
	}
	private int maxMove;
	private Sector currentSector;
	private String name;
	private int playerID;
	private ArrayList <ItemCard> itemCardsHeld = new ArrayList<ItemCard>();
	private ArrayList <Sector> reachableSectors = new ArrayList<Sector>();
	public int getMaxMove() {
		return maxMove;
	}
	public void setMaxMove(int maxMove) {
		this.maxMove = maxMove;
	}
	public Sector getCurrentSector() {
		return currentSector;
	}
	public void setCurrentSector(Sector currentSector) {
		this.currentSector = currentSector;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public ArrayList<ItemCard> getItemCardsHeld() {
		return itemCardsHeld;
	}
	public void setItemCardsHeld(ArrayList<ItemCard> itemCardsHeld) {
		this.itemCardsHeld = itemCardsHeld;
	}
	public ArrayList<Sector> getReachableSectors() {
		return reachableSectors;
	}
	public void setReachableSectors(ArrayList<Sector> reachableSectors) {
		this.reachableSectors = reachableSectors;
	}

	
	
	
}
