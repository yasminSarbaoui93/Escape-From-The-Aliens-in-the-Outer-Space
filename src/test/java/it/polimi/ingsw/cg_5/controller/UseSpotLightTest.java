package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;

import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCardType;

import org.junit.Test;

public class UseSpotLightTest {

	@Test
	public void test() {

				ArrayList<Integer> playersID = new ArrayList<Integer>();
				
				
				for (int i=0 ; i<5; i++){
					playersID.add(i);
				}
			
				GameState matchGalilei1= new GameState (playersID, "Galilei",0);
				matchGalilei1.getCharacterList().get(2).setCurrentSector(matchGalilei1.getMap().takeSector("K06"));
				matchGalilei1.getMap().takeSector("K06").getCharacterList().add(matchGalilei1.getCharacterList().get(2));
	UseSpotLight useSpot = new UseSpotLight(matchGalilei1,ItemCardType.SPOTLIGHT, "J06");
	useSpot.execute();
	System.out.println(useSpot.getSpottedPlayer());
	assert(useSpot.getSpottedPlayer().contains(matchGalilei1.getCharacterList().get(2)));
	}

}
