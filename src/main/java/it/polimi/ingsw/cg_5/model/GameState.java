package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;

public class GameState extends Observable{
	//tutti gli attributi del gioco che potrebbero essere utili per rappresentare una partita
	private Map  map;
	private int round;
	private EscapeHatchDeck escapeHatchDeck;
	private ItemDeck itemDeck;
	private  GameDeck gameDeck;
	private ArrayList<Character> characterList = new ArrayList <Character>();
	private Character currentCharacter;
	private Turn turn= new Turn();
	
	
	public GameState(ArrayList <Integer> IDsofplayers , String choosenMap ){ //PATTERN BUILDER
		//questo è il costruttore di gamestate, inizializziamo la mappa in base a quella scelta per questo gioco,
		//inizializziamo tutti i mazzi,inoltre viene passato un array coi nomi dei giocatori, chiamiamo il metodo
		//set character che si occuperà di creare i player, di creare i character e ritorna la lista dei giocatori
		//siamo pronti per iniziare a giocare, inoltre l'assegnazione sia dei player che dei character + casuale
		//quindi non succederà mai che il primo player sia sempre alieno o umano, oppure che vengono usati sempre prima
		//gli stessi personaggi(tipo the captain piero ceccarella ecc)
		map=new Map(choosenMap);
		round=1;
		escapeHatchDeck= new EscapeHatchDeck();
		itemDeck= new ItemDeck();
		gameDeck=new GameDeck();
		characterList=createCharacterList(IDsofplayers);
		currentCharacter=getCharacterList().get(0);
		turn.setTurnState(TurnState.STARTED);
		}
	
	public ArrayList<Character> getCharacterList() {
		return characterList;
	}
	// i due metodi setAlien/HumanName servono per inserire in una lista i valori degli enum contenenti i nomi dei giocatori
	//questa lista viene passata come parametro quando creiamo i character, visto che il costruttore dei character
	//necessita di una String name come parametro d'ingresso
	public ArrayList <String> setAlienName (){
		ArrayList <String> list = new ArrayList <String>();
		for( AlienName e : AlienName.values()){
			list.add(e.getAlienName());
		}
		Collections.shuffle(list);
		return list;
			}
	public ArrayList <String> setHumanName (){
		ArrayList <String> list = new ArrayList <String>();
		for( HumanName e : HumanName.values()){
			list.add(e.getHumanName());
		}
		Collections.shuffle(list);
		return list;
			}	
	public ArrayList <Character> createCharacterList(ArrayList <Integer> IDsofplayers){
		//questo metodo dato in ingresso il numero di partecipanti al gioco, ritorna una lista di personaggi seguendo
		//le regole del gioco(N° alieni >= N° umani), il metodo si avvale di setAlienName e setHumanName per ottenere
		//i nomi dei personaggi, da passare al costruttore di character
		int humanNumber = IDsofplayers.size()/2;
		int alienNumber = IDsofplayers.size()-humanNumber;
		ArrayList <Character> characterList = new ArrayList <Character>();
		ArrayList <String> alienList = new ArrayList <String>(); 
		ArrayList <String> humanList = new ArrayList <String>(); 
		alienList= setAlienName();
		humanList= setHumanName();
		Collections.shuffle(IDsofplayers);
		for(int j=0; j<humanNumber; j++){//crea umani
			Human human=new Human(humanList.remove(humanList.size()-1),IDsofplayers.remove(IDsofplayers.size()-1));
			human.setCurrentSector(map.takeSector("HUMAN_START"));
			characterList.add(human);
		}
		for(int j=0; j<alienNumber; j++){
			Alien alien=new Alien(alienList.remove(alienList.size()-1),IDsofplayers.remove(IDsofplayers.size()-1));
			alien.setCurrentSector(map.takeSector("ALIEN_START"));
			characterList.add(alien);
		}
		
		return characterList;
	}

	public Map getMap() {
		return map;
	}
	

	public void setMap(Map map) {
		this.map = map;
	}
	

	public ItemDeck getItemDeck() {
		return itemDeck;
	}

	public void setItemDeck(ItemDeck itemDeck) {
		this.itemDeck = itemDeck;
	}

	// sara chiamato dal action attack per eliminare i player dal gioco(zhou)
	public void removeCharacter(Character attackedCharacter){
		
		characterList.remove(attackedCharacter);
	}
	
	public int getRound() {
		return round;
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

	public Character getCurrentCharacter() {
		return currentCharacter;
	}

	public void setCurrentCharacter(Character currentCharacter) {
		this.currentCharacter = currentCharacter;
	}

	public  GameDeck getGameDeck() {
		return gameDeck;
	}

	
	
}
	
