package it.polimi.ingsw.cg_5;

public class ItemCard implements Card {
	
	
	private final ItemCardType itemCardType;
	
	// costruttore 
	
	public ItemCard(ItemCardType itemCardType) {
		
		this.itemCardType = itemCardType;
	}



	public ItemCardType getItemCardType() {
		return itemCardType;
	}



	@Override
	public void cardEffect() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String toString() {
		return "ItemCard (" + itemCardType + ")";
	}
	
	
	

	
	


	

}
