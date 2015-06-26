package it.polimi.ingsw.cg_5.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_5.connection.broker.Broker;
import it.polimi.ingsw.cg_5.connection.broker.BrokerRmi;
import it.polimi.ingsw.cg_5.model.EscapeHatchType;
import it.polimi.ingsw.cg_5.model.GameState;

import java.util.ArrayList;

import org.junit.Test;

public class EscapeMoveTest {

	@Test
	public void test() {
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		for (int i=0 ; i<5; i++){
			playersID.add(i);
		}
		Broker broker = new BrokerRmi("a");
		GameState matchGalilei1= new GameState (playersID, "Galilei",0);
		Match match=new Match(matchGalilei1,0,broker);
		EscapeMove move = new EscapeMove(matchGalilei1,matchGalilei1.getMap().takeSector("V02"),match);
		if(move.checkAction()){
			move.execute();
		
		if( move.getEscapeCard().getEscapeHatchType()==EscapeHatchType.GREEN_SHALLOP){
			assert(matchGalilei1.getWinners().contains(matchGalilei1.getCurrentCharacter()));
		}
		else{
			assert(!matchGalilei1.getWinners().contains(matchGalilei1.getCurrentCharacter()));
		}
		}
	}

}
