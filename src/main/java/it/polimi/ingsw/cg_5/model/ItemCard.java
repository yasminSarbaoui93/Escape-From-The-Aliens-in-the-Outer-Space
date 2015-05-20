package it.polimi.ingsw.cg_5.model;

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
	public String toString() {
		return "ItemCard (" + itemCardType + ")";
	}
	
	
	

	
	


	

}
