package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;


import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;

public class UseSpotLight extends UseItemCard {
	private String sectorToSpotlight;
	ArrayList<Character> spottedPlayer = new ArrayList <Character>();
	public ArrayList<Character> getSpottedPlayer() {
		return spottedPlayer;
	}

	public UseSpotLight(GameState gameState, ItemCardType itemCardType,String sectorToSpotlight){
		super(gameState, itemCardType);
		this.sectorToSpotlight=sectorToSpotlight;
	
	}
	
	@ Override
	public void execute() throws NullPointerException {
		
		if(usingItemCardType==ItemCardType.SPOTLIGHT)  {
			if(gameState.getMap().takeSector(sectorToSpotlight) == null) throw new NullPointerException();
			else{
			spottedPlayer.addAll(gameState.getMap().takeSector(sectorToSpotlight).getCharacterList());
			for(Sector sector : gameState.getMap().takeSector(sectorToSpotlight).getReachableSectors(1, gameState.getMap().takeSector(sectorToSpotlight)) ){
				spottedPlayer.addAll(gameState.getMap().takeSector(sector.getSectorName()).getCharacterList());
			}
							
			}
		}
	}


}
