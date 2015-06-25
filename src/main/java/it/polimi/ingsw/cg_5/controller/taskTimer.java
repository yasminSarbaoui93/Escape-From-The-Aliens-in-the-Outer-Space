package it.polimi.ingsw.cg_5.controller;


import it.polimi.ingsw.cg_5.model.Character;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class taskTimer extends TimerTask {
	private Match match;
	
	public taskTimer(Match match) {
		super();
		this.match = match;
	}

	@Override
	public void run() {
	
	EndTurn endTurn=new EndTurn(this.match.getGameState(), this.match);
	try {
		this.match.getBroker().publish("The time for the Player with ID-"+this.match.getGameState().getCurrentCharacter().getPlayerID()+ " is "
				+ "expired and he will be removed from the game", false);
	} catch (RemoteException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Character timerCharacter=this.match.getGameState().getCurrentCharacter();
	this.match.getGameState().removeCharacter(timerCharacter);
	if(match.isGameOver()){
		match.setMatchState(MatchState.ENDED);
		try {
			this.match.getBroker().publish("The game is ended after a player abandoned the game", false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(match.getMatchState()!=MatchState.ENDED){
		endTurn.execute();
	}
	
	
	else {
		endTurn.execute();
	}
	try {
		this.match.getBroker().publishNumberGame(this.match.getNumberGame(), this.match.getGameState().getCurrentCharacter().getPlayerID());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	}


