package it.polimi.ingsw.cg_5.connection;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.model.Sector;
import it.polimi.ingsw.cg_5.model.TurnState;



/**This class conintains all the attributes that will be sent by the server to the client in answer to every change that the player does to the game state.
 * @author Yasmin
 *
 */
public class PlayerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TurnState turnState;
	private Character yourCharacter;
	private ArrayList <String> reachableSectors = new ArrayList<String>();
	private String messagesToSend;
	private int currentCharacter;
	
	public PlayerDTO(Character character){
		this.yourCharacter = character;
	}
	public PlayerDTO(String messageToSend){
		this.yourCharacter=null;
		this.messagesToSend = messageToSend;
	}
	
	/*************************GETTERS AND SETTERS*********************************/
	
	public void setCurrentCharacter(int currentCharacter) {
		this.currentCharacter = currentCharacter;
	}
	
	public Character getYourCharacter() {
		return yourCharacter;
	}
	
	
	public String getMessageToSend(){
		return this.messagesToSend;
	}
	
	public void setMessageToSend(String message){
		this.messagesToSend = message;
	}

	public ArrayList<String> getReachableSectors() {
		for(Sector sector : yourCharacter.getCurrentSector().getReachableSectors(yourCharacter.getMaxMove(),
				yourCharacter.getCurrentSector())){
		reachableSectors.add(sector.getSectorName());
				}
		return reachableSectors;
	}
	@Override
	public String toString() {
		return yourCharacter+", "+messagesToSend;
	}
	public TurnState getTurnState() {
		return turnState;
	}
	public void setTurnState(TurnState turnState) {
		this.turnState = turnState;
	}
	
	public void setYourCharacter(Character yourCharacter) {
		this.yourCharacter = yourCharacter;
	}
	public int getCurrentCharacter() {
		return currentCharacter;
	}
	

	
}
