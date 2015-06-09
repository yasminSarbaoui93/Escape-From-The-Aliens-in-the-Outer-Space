package it.polimi.ingsw.cg_5.controller;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.cg_5.model.DangerousSector;
import it.polimi.ingsw.cg_5.model.GameCardType;
import it.polimi.ingsw.cg_5.model.GameState;
import it.polimi.ingsw.cg_5.model.ItemCard;
import it.polimi.ingsw.cg_5.model.ItemCardType;
import it.polimi.ingsw.cg_5.model.SafeSector;
import it.polimi.ingsw.cg_5.model.Sector;
import it.polimi.ingsw.cg_5.model.TurnState;

public class MainProvaGioco {
	
	

	public static void main(String[] args) {
		
		ArrayList<Integer> playersID = new ArrayList<Integer>();
		
		for (int i=0 ; i<2; i++){
			playersID.add(i);
		}
		
		GameState gameState = new GameState(playersID,"GALILEI");
		
		Match match =new Match(gameState, 0);
		
		System.out.println("il gioco e' iniziato e i player sono"+gameState.getCharacterList());
		
		//System.out.println(""+gameState.getCurrentCharacter().getCurrentSector().getReachableSectors(gameState.getCurrentCharacter().getMaxMove(), gameState.getCurrentCharacter().getCurrentSector()));
		
		while(match.getMatchState()==MatchState.RUNNING){
			System.out.println("siamo nel Round "+gameState.getRound());
			System.out.println("\ne' il turno di "+gameState.getCurrentCharacter());
		
			
			Scanner scannerIn= new Scanner(System.in);
			String comando = null;
			boolean turnRunning=true;
			
			while(turnRunning){
				
			if(gameState.getTurn().getTurnState().equals(TurnState.STARTED)){
			System.out.println("cosa Vuoi Fare ? USECARD OR MOVE");
			comando =scannerIn.next().toUpperCase();
			//System.out.println(comando);
			}
			
			if(gameState.getTurn().getTurnState().equals(TurnState.HASMOVED)){
					System.out.println(" vuoi ATTACK or  DRAW or ENDTURN or USECARD/DISCARDCARD?");
					comando=scannerIn.next().toUpperCase();
				}
			if(gameState.getTurn().getTurnState().equals(TurnState.HASATTACKORDRAWN)){
				System.out.println("vuoi ENDTURN O USECARD OR DISCARDCARD");
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
					
					
					Move move= new Move(gameState,sectorToMove);
					if(move.checkAction()){
						move.execute();
						System.out.println("ti sei mosso con Successo IN "+gameState.getCurrentCharacter().getCurrentSector());
					}
						else
							System.out.println("comando Errato bro");
					
				
			}
				
				if(comando.equals("ATTACK")){
					System.out.println("\n Attacco in esecuzione ...");
					Attack attack = new Attack(gameState);
					if(attack.checkAction()==true){
						attack.execute();
						System.out.println("attacco eseguito");
						
						if(attack.getCharacterToKill().size()==0)
							System.out.println("attacco e' andato  a vuoto");
						else{
							System.out.println("sono stati uccisi"+attack.getCharacterToKill());
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
						System.out.println("la carta pescata e' "+draw.getDrawnCard());
						
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
						
						System.out.println("hai pescato"+drawnItemCard);
						System.out.println("ora la tua mano ha "+gameState.getCurrentCharacter().getItemPlayerCard().size()+ "itemCard");
						if(gameState.getCurrentCharacter().getItemPlayerCard().size()==4)
							System.out.println("Ricorda che dovrai usare una Card o scartare per concludere il turno");
							
							
					}else System.out.println("nn e' Stato possibile Pescare la Carta Item");
					}
				}else System.out.println("non puoi pescare i motivi possono essere : momento sbagliato, sei su un settore Safe");
			
				}
				
			if(comando.equals("USECARD"))	{
				String spotSector = null;
				ItemCardType tipoCarta = null;
				boolean existingSector = true;
				System.out.println("le tue carte in Mano sono"+gameState.getCurrentCharacter().getItemPlayerCard());
				System.out.println("inserisci il tipo di carta che vuoi usare tra : ATTACK(AT),SEDATIVE(SE),SPOTLIGHT(SP),TELEPORT(TE),ADRENALINA(AD)");
				comandoSpecifico= scannerIn.next().toUpperCase();
				if(comandoSpecifico.equals("AT"))
					tipoCarta=ItemCardType.ATTACK;
				if(comandoSpecifico.equals("SE"))
					tipoCarta=ItemCardType.SEDATIVES;
				if(comandoSpecifico.equalsIgnoreCase("SP")){
					tipoCarta=ItemCardType.SPOTLIGHT;
					do{
						try{
					System.out.println("inserisci il Nome del settore che vuoi Spottare");
						spotSector= scannerIn.next().toUpperCase();
						gameState.getMap().takeSector(spotSector);
						existingSector=true;
						}catch(NullPointerException e){
							System.out.println("Settore non esistente, riprova..");
							existingSector = false;
						}
					}while(existingSector==false);
				}
				
				if(comandoSpecifico.equals("TE"))
					tipoCarta=ItemCardType.TELEPORT;
				if(comandoSpecifico.equals("AD"))
					tipoCarta=ItemCardType.ADRENALINE;
				
				UseItemCard useCard = new UseItemCard(gameState, tipoCarta,spotSector);
				if(useCard.checkAction()){
					useCard.execute();
					System.out.println("la Carta "+tipoCarta+" è stata usata con sucesso");
				}else System.out.println("comando errato: motivi sei alieno non hai la carta");
					
				
		        
				
			}
			// FINE USE CARD	
				
			if(comando.equals("ENDTURN")){
				if(match.isGameOver()){
					match.setMatchState(MatchState.ENDED);
				}
				EndTurn endTurn= new EndTurn(gameState);
				if(endTurn.checkAction()){
					endTurn.execute();
				turnRunning=false;	
				}else System.out.println("non puoi Terminare turn al momento");
				
			}
			if(comando.equals("DISCARDCARD")){
				ItemCardType tipoCarta = null;
				System.out.println("le tue carte in Mano sono"+gameState.getCurrentCharacter().getItemPlayerCard());
				System.out.println("inserisci il tipo di carta che vuoi usare tra : ATTACK(AT),SEDATIVE(SE),SPOTLIGHT(SP),TELEPORT(TE),ADRENALINA(AD)");
				comandoSpecifico= scannerIn.next().toUpperCase();
				if(comandoSpecifico.equals("AT"))
					tipoCarta=ItemCardType.ATTACK;
				if(comandoSpecifico.equals("SE"))
					tipoCarta=ItemCardType.SEDATIVES;
				if(comandoSpecifico.equalsIgnoreCase("SP"))
					tipoCarta=ItemCardType.SPOTLIGHT;
				
				if(comandoSpecifico.equals("TE"))
					tipoCarta=ItemCardType.TELEPORT;
				if(comandoSpecifico.equals("AD"))
					tipoCarta=ItemCardType.ADRENALINE;
				
				
				DiscardItemCard discard= new DiscardItemCard(gameState, tipoCarta);
				if(discard.checkAction()){
					discard.execute();
					System.out.println("rimosso carta con successo");
				}else System.out.println("operazione Non Valida");
			}
			
			}
			
			
			
	
			
		}
			
		
		
	
	}

}
