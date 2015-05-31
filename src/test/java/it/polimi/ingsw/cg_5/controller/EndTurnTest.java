package it.polimi.ingsw.cg_5.controller;

import static org.junit.Assert.*;
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
		
		GameState stateprova= new GameState(playersID,"GALILEI");
		EndTurn endTurn= new EndTurn(stateprova);
		System.out.println(stateprova.getCurrentCharacter());
		System.out.println(stateprova.getTurn().getTurnState());
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		System.out.println(stateprova.getTurn().getTurnState());
		if(endTurn.checkEndTurn()){
			endTurn.execute();
		}
		System.out.println(stateprova.getCurrentCharacter());
		System.out.println(stateprova.getTurn().getTurnState());
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkEndTurn()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkEndTurn()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkEndTurn()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkEndTurn()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		if(endTurn.checkEndTurn()){
			endTurn.execute();
		}
		stateprova.getTurn().setTurnState(TurnState.HASATTACKORDRAWN);
		System.out.println(stateprova.getCurrentCharacter());
		System.out.println(stateprova.getTurn().getTurnState());
		System.out.println(stateprova.getRound());
		
	}

}
