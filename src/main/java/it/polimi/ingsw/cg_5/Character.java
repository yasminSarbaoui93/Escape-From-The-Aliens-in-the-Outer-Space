package it.polimi.ingsw.cg_5;

import java.util.ArrayList;
//umano emula le azioni dell  alieno quando usa la carta attacck e adrenaline. quindi pensiamo che si possa fare un metodo
//unico invece per le  carte sedativo ,spotlight,defence saranno metodi solo dell umano.
//occorrono costruttori diversi per alieno e umano perche hanno valori(maxmove canAttack) diversi al  momento  della  creazione--
public abstract class Character {
	private Sector currentSector;
	private String character;
	
	//Creo il costruttore generico del personaggio (nell'erede super(string character))
	public Character(String character){
		this.character = character;
	}
	
	//dopo aver creato la variabile current secotor serve un setter per settare il settore attuale del giocatore e il getter
	public void setCurrentSector(Sector currentSector) {
		this.currentSector = currentSector;
	}
	public Sector getCurrentSector() {
		return currentSector;
	}
	
	//Il metodo can attack viene implementato diversamente a seconda se è alieno o umano
	//l'alieno puà attaccare sempre in ogni momento (per regole avanzate, dopo aver ucciso un umano puo muoversi di 3 caselle)
	//l'umano puo attaccare solo se nella sua lista di itemcards ha la carta attacco
	public abstract boolean canAttack(Sector destinationSector);
	
	
		
	
	
}
