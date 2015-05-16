package it.polimi.ingsw.cg_5;

public abstract class GameCard extends Card{
	//attributo che indica se vi e presente icona delle carte Item sulla carta 
	
private boolean itemIcon;

//costruttore
	public GameCard(boolean itemIcon) {
	this.itemIcon=itemIcon;
}


	@Override
	public abstract void cardEffect(); 

}
