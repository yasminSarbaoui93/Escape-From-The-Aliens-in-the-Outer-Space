package it.polimi.ingsw.cg_5.controller;

import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCardType;

public class UseSpotLight extends UseItemCard {
	private String sectorToSpotlight;
	public UseSpotLight(GameState gameState, ItemCardType itemCardType,String sectorToSpotlight){
		super(gameState, itemCardType);
		this.sectorToSpotlight=sectorToSpotlight;
	
		// TODO Auto-generated constructor stub
	}
	
	@ Override
	public void execute() throws NullPointerException {
		
		if(usingItemCardType==ItemCardType.SPOTLIGHT)  {
			if(gameState.getMap().takeSector(sectorToSpotlight) == null) throw new NullPointerException();
		}
	}


}
