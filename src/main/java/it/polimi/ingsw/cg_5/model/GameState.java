package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
import java.util.Observable;

public class GameState extends Observable{
	//tutti gli attributi del gioco che potrebbero essere utili per rappresentare una partita
	private Map  map;
	private int turn;
	private EscapeHatchDeck escapeHatchDeck;
	private ItemDeck itemDeck;
	private GameDeck gameDeck;
	private ArrayList<Player> playerList;
	
	// sara chiamato dal action mattack per eliminare i player dal gioco
	public void removePlayer(Player attackedPlayer){
		
		playerList.remove(attackedPlayer);
	}
	
	public int getTurn() {
		return turn;
	}
}
	