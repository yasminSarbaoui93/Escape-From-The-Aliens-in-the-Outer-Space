package it.polimi.ingsw.cg_5.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_5.connection.broker.Broker;
import it.polimi.ingsw.cg_5.connection.broker.BrokerRmi;
import it.polimi.ingsw.cg_5.model.GameState;

import java.util.ArrayList;

import org.junit.Test;

public class MatchTest {

	@Test
	public void test() {
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		
		for (int i=0 ; i<5; i++){
			playersID.add(i);
		}
		Broker broker = new BrokerRmi("a");
		GameState matchGalilei1= new GameState (playersID, "Galilei",0);
		Match match=new Match(matchGalilei1,0,broker);
		match.setMatchState(MatchState.RUNNING);
		assertEquals(matchGalilei1,match.getGameState());
		int m=0;
		assertEquals(m,match.getNumberGame());
		assertEquals(broker,match.getBroker());
		match.getGameState().setRound(40);
		assertEquals(true,match.isGameOver());
		
	}

}
