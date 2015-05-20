package it.polimi.ingsw.cg_5.model;

public class GameCard implements Card{
	//attributo che indica se vi e presente l'icona delle carte Item sulla carta 
	private final GameCardType gameCardType;
    private boolean itemIcon;

//costruttore
	public GameCard(boolean itemIcon,GameCardType gameCardType) {
	this.itemIcon=itemIcon;
	this.gameCardType=gameCardType;
}

	public GameCardType getGameCardType() {
		return gameCardType;
	}

	@Override
	public String toString() {
		return "(" + gameCardType + ","
				+ itemIcon + ")";
	}
	
	
	
	
	

	



}
