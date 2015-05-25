package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;

import it.polimi.ingsw.cg_5.model.*;

public class GameManager {
	private int gameIndex=0;
	private ArrayList <Game> gameList;
	
	public void GameCreator(String playerList [] ){
		gameList.add(new Game(new GameState(playerList, "Galilei"), gameIndex));
		gameIndex++;
	}
	
}
