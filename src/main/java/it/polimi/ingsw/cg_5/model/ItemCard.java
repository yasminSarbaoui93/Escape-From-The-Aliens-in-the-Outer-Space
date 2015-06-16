package it.polimi.ingsw.cg_5.model;

import java.io.Serializable;

public class ItemCard implements Card,Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
