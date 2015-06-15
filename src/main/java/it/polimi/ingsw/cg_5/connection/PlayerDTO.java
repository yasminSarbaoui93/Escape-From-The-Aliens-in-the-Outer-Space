package it.polimi.ingsw.cg_5.connection;

import java.io.Serializable;
import java.util.ArrayList;
import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.model.Sector;


//Those are all the attributes that will be sent by the server to the client
//in answer to every change that the player does to the game state.
public class PlayerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Character yourCharacter;
	private ArrayList <Sector> reachableSectors = new ArrayList<Sector>();
	private String messagesToSend;
	
	public PlayerDTO(Character character){
		this.yourCharacter = character;
	}
	/*************************GETTERS AND SETTERS*********************************/
	

	
	public Character getYourCharacter() {
		return yourCharacter;
	}
	
	
	public String getMessageToSend(){
		return this.messagesToSend;
	}
	
	public void setMessageToSend(String message){
		this.messagesToSend = message;
	}

	public ArrayList<Sector> getReachableSectors() {
		reachableSectors.addAll(yourCharacter.getCurrentSector().getReachableSectors(yourCharacter.getMaxMove(), yourCharacter.getCurrentSector()));
		return reachableSectors;
	}
	@Override
	public String toString() {
		return yourCharacter+", "+messagesToSend;
	}
	
	
	

	
}
