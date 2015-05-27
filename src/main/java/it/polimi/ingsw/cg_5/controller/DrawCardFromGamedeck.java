package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.Deck;
import it.polimi.ingsw.cg_5.model.GameDeck;
import it.polimi.ingsw.cg_5.model.GameState;

public class DrawCardFromGamedeck extends Action {
 GameDeck gameDeck;
	public DrawCardFromGamedeck(GameState gameState, GameDeck gameDeck) {
		super(gameState);
		this.gameDeck=gameDeck;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		//If(gameState.getGameDeck().removeCard().getGameCardType.....logica per usare le carte
	}

}
