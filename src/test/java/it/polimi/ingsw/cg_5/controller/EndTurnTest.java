package it.polimi.ingsw.cg_5.controller;


import it.polimi.ingsw.cg_5.connection.broker.Broker;
import it.polimi.ingsw.cg_5.connection.broker.BrokerRmi;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.TurnState;

import java.util.ArrayList;

import org.junit.Test;

public class EndTurnTest {

	@Test
	public void test() {
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		for (int i=0 ; i<5; i++){
			playersID.add(i);
		}
		Broker broker = new BrokerRmi("BrokerFake");
		GameState stateprova= new GameState(playersID,"GALILEI",0);
		Match match = new Match(stateprova, 0, broker);
		EndTurn endTurn= new EndTurn(stateprova, match);
		System.out.println(stateprova.getCurrentCharacter());
		System.out.println(stateprova.getTurn().getTurnState());
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		System.out.println(stateprova.getTurn().getTurnState());
		if(endTurn.checkAction()){
			endTurn.execute();
		}
		System.out.println(stateprova.getCurrentCharacter());
		System.out.println(stateprova.getTurn().getTurnState());
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkAction()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkAction()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkAction()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkAction()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkAction()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		System.out.println(stateprova.getCurrentCharacter());
		System.out.println(stateprova.getTurn().getTurnState());
		System.out.println(stateprova.getRound());
		
	}

}
