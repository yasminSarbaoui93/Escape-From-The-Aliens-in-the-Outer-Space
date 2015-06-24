package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.cg_5.connection.broker.BrokerRmi;
import it.polimi.ingsw.cg_5.model.EscapeHatchType;
import it.polimi.ingsw.cg_5.model.EscapeSector;
import it.polimi.ingsw.cg_5.model.GameCardType;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.ItemCardType;
import it.polimi.ingsw.cg_5.model.Sector;
import it.polimi.ingsw.cg_5.model.TurnState;

public class MainProvaGioco {
	
	

	public static void main(String[] args) {
		
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		BrokerRmi broker= new BrokerRmi("ciao");
		for (int i=0 ; i<4; i++){
			playersID.add(i);
		}
		
		GameState gameState = new GameState(playersID, "GALILEI",0);
		
		Match match =new Match(gameState, 0,broker);
		//match.getGameState().setRound(39);
		
		System.out.println("il gioco e' iniziato e i player sono"+gameState.getCharacterList());
		
		//System.out.println(""+gameState.getCurrentCharacter().getCurrentSector().getReachableSectors(gameState.getCurrentCharacter().getMaxMove(), gameState.getCurrentCharacter().getCurrentSector()));
		Scanner scannerIn= new Scanner(System.in);
		
		while(match.getMatchState()==MatchState.RUNNING){
			
			String comando = null;
			boolean turnRunning=true;
			
			if(match.isGameOver()){
				System.out.println("ops i turn sono finiti \n IL Gioco è Terminato");
				match.setMatchState(MatchState.ENDED);
				turnRunning=false;
			}
			
			else{
			System.out.println("\n\n\nsiamo nel Round "+gameState.getRound());
			System.out.println("\ne' il turno di "+gameState.getCurrentCharacter());
			}
			
			while(turnRunning){
				
				
				
			if(gameState.getTurn().getTurnState().equals(TurnState.STARTED)){
			System.out.println("\n \ncosa Vuoi Fare ? USECARD OR MOVE");
			comando =scannerIn.next().toUpperCase();
			//System.out.println(comando);
			}
			
			if(gameState.getTurn().getTurnState().equals(TurnState.HASMOVED)){
					System.out.println(" \n\nvuoi ATTACK or  DRAW or ENDTURN or USECARD/DISCARDCARD?");
					comando=scannerIn.next().toUpperCase();
				}
			if(gameState.getTurn().getTurnState().equals(TurnState.HASATTACKORDRAWN)){
				System.out.println("\n\nvuoi ENDTURN O USECARD OR DISCARDCARD");
				comando=scannerIn.next().toUpperCase();
			}
				String comandoSpecifico = null;
				
				if(comando.equals("MOVE")){	
					
					boolean existingSector=true;
					Sector sectorToMove = null;
					
					do{
						try{
					System.out.println("\nseleziona il settore in cui vuoi muoverti:"+
				gameState.getCurrentCharacter().getCurrentSector().getReachableSectors(gameState.getCurrentCharacter().getMaxMove(), gameState.getCurrentCharacter().getCurrentSector()));
						comandoSpecifico=scannerIn.next().toUpperCase();
						 sectorToMove=gameState.getMap().takeSector(comandoSpecifico);
						 existingSector=true;
						}catch(NullPointerException e){
							System.out.println("settore non esistente , riprova...");
							existingSector=false;
						}
					}while(existingSector==false);
					
					if(sectorToMove.getClass()!=EscapeSector.class){
					
						Move move= new Move(gameState,sectorToMove);
						if(move.checkAction()){
							move.execute();
							System.out.println("ti sei mosso correttamente in "+gameState.getCurrentCharacter().getCurrentSector());
					}
						else
							System.out.println("comando Errato bro");
					}
					else{
						EscapeMove escapeMove=new EscapeMove(gameState,sectorToMove ,match);
						if(escapeMove.checkAction()){
							escapeMove.execute();
							if(escapeMove.getEscapeCard().getEscapeHatchType()==EscapeHatchType.GREEN_SHALLOP){
								System.out.println("il Giocatore"+gameState.getCurrentCharacter()+" è Fuggito\n Ora non sara piu utilizzabile");
								turnRunning=false;
							}
							else
								System.out.println("La scialuppa è distrutta");
						}
					
					}
					
				}
				if(comando.equals("ATTACK")){
					System.out.println("\n Attacco in esecuzione ...");
					Attack attack = new Attack(gameState,match);
					if(attack.checkAction()==true){
						attack.execute();
						System.out.println("attacco eseguito!");
						if(!attack.getSafeCharacter().isEmpty()){
							System.out.println(attack.getSafeCharacter()+"si e' Salvato usando Defence CArd");
						}
						if(attack.getPlayerToKill().size()==0)
							System.out.println("attacco e' andato  a vuoto!");
						else{
							System.out.println("sono stati uccisi"+attack.getPlayerToKill());
							if(match.isGameOver()){
								System.out.println("\n\n  il Gioco e' finito");
								match.setMatchState(MatchState.ENDED);
								turnRunning=false;
							}
						}
				}else System.out.println("non puoi attaccare i questo momento");
				}
				
				if(comando.equals("DRAW")){
					
					boolean existingSector=true;
					
					DrawCardFromGamedeck draw = new DrawCardFromGamedeck(gameState);
					if(draw.checkAction()){
						draw.execute();
						System.out.println("\nla carta pescata e' "+draw.getDrawnCard());
						
					if(draw.getDrawnCard().getGameCardType()==GameCardType.NOISE_ANY_SECTOR){
						//sceglie anche il settore che bleffera'
						
						System.out.println("hai pescato noise any sector");
						do{
							try{
						System.out.println("inserisci il Nome del settore che vuoi Bleffare");
							comandoSpecifico= scannerIn.next().toUpperCase();
							gameState.getMap().takeSector(comandoSpecifico);
							existingSector=true;
							}catch(NullPointerException e){
								System.out.println("Settore non esistente, riprova..");
								existingSector = false;
							}
						}while(existingSector==false);
						System.out.println(gameState.getCurrentCharacter()+" sono in "+comandoSpecifico);
						
						}
					if(draw.getDrawnCard().getGameCardType()==GameCardType.NOISE_YOUR_SECTOR){
						
						
						System.out.println(gameState.getCurrentCharacter()+" sono in "+gameState.getCurrentCharacter().getCurrentSector());	
					}
					
					if(draw.getDrawnCard().getGameCardType()==GameCardType.SILENCE ){
						//stampa a tutti Silence
						System.out.println("silence");
					
					}
					//  dovro trovare un posto dove mettere il check che il deck  non sia vuoto- o se è vuoto controllare che usedItemDeck 
					//non sia vuoto
					if(draw.getDrawnCard().isItemIcon()==true ){
						if(draw.checkItemDecks()){
						ItemCard drawnItemCard =gameState.getItemDeck().removeCard();
			
						gameState.getCurrentCharacter().getItemPlayerCard().add(drawnItemCard);
						
						System.out.println("\n hai pescato"+drawnItemCard);
						System.out.println("ora la tua mano ha "+gameState.getCurrentCharacter().getItemPlayerCard().size()+ "itemCard");
						if(gameState.getCurrentCharacter().getItemPlayerCard().size()==4)
							System.out.println("\nRicorda che dovrai usare una Card o scartare per concludere il turno");
							
							
					}else System.out.println("\n non e' Stato possibile Pescare la Carta Item");
					}
				}else System.out.println("\n non puoi pescare i motivi possono essere : momento sbagliato, sei su un settore Safe");
			
				}
				
			if(comando.equals("USECARD"))	{
				String spotSector = null;
				ItemCardType tipoCarta = null;
				boolean existingSector = true;
				System.out.println("\n le tue carte in Mano sono"+gameState.getCurrentCharacter().getItemPlayerCard());
				System.out.println("\n inserisci il tipo di carta che vuoi usare tra : ATTACK(AT),SEDATIVE(SE),SPOTLIGHT(SP),TELEPORT(TE),ADRENALINA(AD)");
				comandoSpecifico= scannerIn.next().toUpperCase();
				if(comandoSpecifico.equals("AT"))
					tipoCarta=ItemCardType.ATTACK;
				if(comandoSpecifico.equals("SE"))
					tipoCarta=ItemCardType.SEDATIVES;
				if(comandoSpecifico.equals("SP")){
					tipoCarta=ItemCardType.SPOTLIGHT;
					do{
						try{
					System.out.println("\n inserisci il Nome del settore che vuoi Spottare");
						spotSector= scannerIn.next().toUpperCase();
						gameState.getMap().takeSector(spotSector);
						existingSector=true;
						}catch(NullPointerException e){
							System.out.println("\n Settore non esistente, riprova..");
							existingSector = false;
						}
					}while(existingSector==false);
					
				}
				
				if(comandoSpecifico.equals("TE"))
					tipoCarta=ItemCardType.TELEPORT;
				if(comandoSpecifico.equals("AD"))
					tipoCarta=ItemCardType.ADRENALINE;
				
				if(comandoSpecifico.equals("SP")){
						UseSpotLight useSpotLight= new UseSpotLight(gameState, tipoCarta,spotSector);
						if(useSpotLight.checkAction()){
					System.out.println("i player presenti in "+spotSector+"sono "+gameState.getMap().takeSector(spotSector).getCharacterList());
					useSpotLight.execute();
					System.out.println("\n la Carta "+tipoCarta+" è stata usata con sucesso");
				}else System.out.println("check ha dato false ");
						
				}else{
					
				
				UseItemCard useCard = new UseItemCard(gameState, tipoCarta);
				if(useCard.checkAction()){
					
					useCard.execute();
					System.out.println("\n la Carta "+tipoCarta+" è stata usata con successo");
				}else System.out.println("\n comando errato: motivi sei alieno non hai la carta");
					
				
				} 
				
			}
			// FINE USE CARD	
				
			if(comando.equals("ENDTURN")){
				
				EndTurn endTurn= new EndTurn(gameState,match);
				if(endTurn.checkAction()){
					endTurn.execute();
				turnRunning=false;	
				}else System.out.println("\n non puoi Terminare turn al momento");
				
			}
			if(comando.equals("DISCARDCARD")){
				ItemCardType tipoCarta = null;
				System.out.println("\n le tue carte in Mano sono"+gameState.getCurrentCharacter().getItemPlayerCard());
				System.out.println("\n inserisci il tipo di carta che vuoi usare tra : ATTACK(AT),SEDATIVE(SE),SPOTLIGHT(SP),TELEPORT(TE),ADRENALINA(AD)");
				comandoSpecifico= scannerIn.next().toUpperCase();
				if(comandoSpecifico.equals("AT"))
					tipoCarta=ItemCardType.ATTACK;
				if(comandoSpecifico.equals("SE"))
					tipoCarta=ItemCardType.SEDATIVES;
				if(comandoSpecifico.equals("SP"))
					tipoCarta=ItemCardType.SPOTLIGHT;
				
				if(comandoSpecifico.equals("TE"))
					tipoCarta=ItemCardType.TELEPORT;
				if(comandoSpecifico.equals("AD"))
					tipoCarta=ItemCardType.ADRENALINE;
				
				
				DiscardItemCard discard= new DiscardItemCard(gameState, tipoCarta);
				if(discard.checkAction()){
					discard.execute();
					System.out.println("\n rimosso carta con successo");
				}else System.out.println("\n operazione Non Valida");
			}
			
			}
			
			
			
	
			
		}
		

		scannerIn.close();

			
		
		
	
	}

}
