package it.polimi.ingsw.cg_5.controller;
// da decidere poi come si chiamera questa classe
public class Checks {
	
	// viene da pensare che la divisione in turno in varie fasi sia la cosa piu sensata
		//supponendo che il turno venga diviso in   1 move .  2 attacck o draw (in base al settore destinazione
		//(cioe' il nuovo currentSector del player ) 3 end turn
		// se si arriva all' checks questo controllo sara' gia'  passato 
	
	// checkMove .. dato che arrivato a questo punto sapremo che il player puo muoversi ed e' nella fase  del turno giusto
	// quello  che manca e' che il settore destinazione sia contenuto nel getreachable sector.. e sarebbe dupicazione di codice dato. che pauz ha fatto gia if dentro
	// action ... penso che sia giusto togliere quella parte e metterla nel check
	
	/* public boolean checkMove(){
	 * if(	gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector().getReachableSectors
			(gameState.getCurrentPlayer().getPlayerCharacter().getMaxMove(),
			gameState.getCurrentPlayer().getPlayerCharacter().getCurrentSector()).contains(this.destinationSector) )
			return true;
			
			else return false;
			}
	 */
	
	/* checkDrawn from gameDeck  sempre per la suddivisione del turno non avremo problemi per se attack o se pesca.
	 * perche' se esegue uno delle due azioni lo step del turn avanzera quindi se riceve di  nuovo una richiesta di attack o drawn
	 * verra rigettata.. 
	 * penso  che ci voglia un controllo sul tipo di settore in cui capitera' il player
	 * 
	 * public boolean checkDrawGameDeck(){
	 * if(currentPlayer.getCurrentSector==Dangerous || condizione che il player non usare itemCard che permette di non pescare)
	 * 	return true;
	 * else return false;
	 * }
	 * public boolean checkAttack lo fa gia execute di pauz
	 *
	 */
	
	/*RULES?
	 * 
	 * checkHumanItheGame  dopo  azioni di scialuppa green e attack  bisognera controllare se ci sianoancora human in gioco se si si
	 * continua altrimenti il gioco finisce  da decidere poi come stabiliamo i vincitori e i vinti....
	 * 
	 * public boolean checkHumanInGame(){
	 * gameState.getPlayerList  scorrere ed vedere uno ad uno se ce umano
	 * se  si return true
	 * 
	 * }
	 * 
	 */
	
/* checkDrawFromItem deck  penso che non serva .. dato che e' una azione obligatoria se si pesca una Carta con l'icona
 * da capire come impleteremo tutte le CardEffect sia dei GameCards che itemsCard..
 * forse il controllo che la carta non puo essere pescata per il  fatto che ildeck sia vuoto. (tutte le itemCard sono in mano ai giocatori)
 * sto check non porta conseguenze.. 
 */

	/* checkCanUseitemCard controlla che la carta che sia voglia usare sia presente in mano al player
	 * immagino  che nello schermo  ci siano tutte le figure delle carte magari quelli che ha il giocatore,sono  messi in rilievo  o un numerino che indichi la
	 * la quatita' di carte di quel itemCard
	 * 
	 */
	
	/*vari check per controllare se deck sia vuoto.. ma qui penso che basti un size- all'interno dell action draw
	 *  caso itemdeck e gamedeck.
	 * 
	 */
	 
}


