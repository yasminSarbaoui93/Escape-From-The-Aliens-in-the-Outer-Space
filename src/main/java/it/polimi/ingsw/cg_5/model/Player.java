package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;

public class Player {

	//Add and remove for arrayList
	private final String nickName;
	private ArrayList <ItemCard> itemPlayerCard;
	private Character playerCharacter ; 
	private boolean alreadyDrawn;

	
	
	public Player(String nickName){
		this.nickName=nickName;
	}
	
	public String getNickName() {
		return nickName;
	}

	//Getter and setter to associate a character to each player. This will be done randomly
	public Character getPlayerCharacter() {
		return playerCharacter;
	}

	public void setPlayerCharacter(Character playerCharacter) {
		this.playerCharacter = playerCharacter;
	}

	//Getter and setter to check if the player's already has drawn a card
	public boolean AlreadyDrawn() {
		return alreadyDrawn;
	}

	public void setYetDraw(boolean alreadyDrawn) {
		this.alreadyDrawn = alreadyDrawn;
	}

}
