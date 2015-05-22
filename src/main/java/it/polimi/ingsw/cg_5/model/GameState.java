package it.polimi.ingsw.cg_5.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;

public class GameState extends Observable{
	//tutti gli attributi del gioco che potrebbero essere utili per rappresentare una partita
	private Map  map= new Map();
	private int turn;
	private EscapeHatchDeck escapeHatchDeck;
	private ItemDeck itemDeck;
	private static GameDeck gameDeck;
	private HashSet<Player> playerList = new HashSet <Player>();
	
	public GameState(String players [] /* String map */){
		//questo è il costruttore di gamestate, inizializziamo la mappa in base a quella scelta per questo gioco,
		//inizializziamo tutti i mazzi,inoltre viene passato un array coi nomi dei giocatori, chiamiamo il metodo
		//set character che si occuperà di creare i player, di creare i character e ritorna la lista dei giocatori
		//siamo pronti per iniziare a giocare, inoltre l'assegnazione sia dei player che dei character + casuale
		//quindi non succederà mai che il primo player sia sempre alieno o umano, oppure che vengono usati sempre prima
		//gli stessi personaggi(tipo the captain piero ceccarella ecc)
		map.Generator();
		map.AddBorders();
		turn=1;
		escapeHatchDeck= new EscapeHatchDeck();
		itemDeck= new ItemDeck();
		gameDeck = new GameDeck();
		playerList=setCharacter(players);
		}
	
	public HashSet<Player> getPlayerList() {
		return playerList;
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

	
	public HashSet <Player> setCharacter(String players []){
		//questo metodo è il fulcro della creazione dei giocatori, viene passata l'array coi nomi dei giocatori,
		//questi vengono creati e gli viene assegnato casualmente un personaggio, setCharacter utilizza il metodo
		//createCharacterList per creare una lista di personaggi da assegnare ai giocatori
		 HashSet <Player> playerList= new  HashSet <Player> ();
		 ArrayList <Character> characterList= new ArrayList <Character> ();
		 characterList=createCharacterList(players.length);
		 Collections.shuffle(characterList);
		for(int i=0; i<players.length; i++){
			Player player= new Player(players[i]);
			player.setPlayerCharacter(characterList.remove(characterList.size()-1)); //getPlayerList()
			playerList.add(player);			
		}
		return playerList;
	}
	
	public ArrayList <Character> createCharacterList(int i){
		//questo metodo dato in ingresso il numero di partecipanti al gioco, ritorna una lista di personaggi seguendo
		//le regole del gioco(N° alieni >= N° umani), il metodo si avvale di setAlienName e setHumanName per ottenere
		//i nomi dei personaggi, da passare al costruttore di character
		int humanNumber = i/2;
		int alienNumber = i-humanNumber;
		ArrayList <Character> characterList = new ArrayList <Character>();
		ArrayList <String> alienList = new ArrayList <String>(); 
		ArrayList <String> humanList = new ArrayList <String>(); 
		alienList= setAlienName();
		humanList= setHumanName();
		for(int j=0; j<humanNumber; j++){//crea umani
			Human human=new Human(humanList.remove(humanList.size()-1));
			human.setCurrentSector(map.takeSector("HUMAN_START"));
			characterList.add(human);
		}
		for(int j=0; j<alienNumber; j++){
			Alien alien=new Alien(alienList.remove(alienList.size()-1));
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

	// sara chiamato dal action attack per eliminare i player dal gioco(zhou)
	public void removePlayer(Player attackedPlayer){
		
		playerList.remove(attackedPlayer);
	}
	
	public int getTurn() {
		return turn;
	}
}
	