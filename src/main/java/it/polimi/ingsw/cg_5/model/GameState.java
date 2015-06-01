package it.polimi.ingsw.cg_5.model;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Collections;
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
	private final int MAX_NUM_ROUND;

	
	
	
	/**
	 * Costructor of a basic Game state; the map initalized is based on the string passed as a parameter.
	 * All the decks are initialized and the characters are assigned casually to each player
	 * @param IDsofplayers: list of all the players that joined the game (created by the controller)
	 * @param choosenMap: map where the players will start the game
	 */
	public GameState(ArrayList <Integer> IDsofplayers , String choosenMap ){
		
		map=new Map(choosenMap);
		round=1;
		escapeHatchDeck= new EscapeHatchDeck();
		itemDeck= new ItemDeck();
		gameDeck=new GameDeck();
		characterList=createCharacterList(IDsofplayers);
		this.MAX_NUM_ROUND = 39;
		currentCharacter=getCharacterList().get(0);
		turn.setTurnState(TurnState.STARTED);
	}

	
	/**This method adds in a list, the enum values containing the players' names. This list is passed as a parameter when
	 * the characters are created since the character's constructor needs a string name as parameter.
	 * @return The list of alien names.
	 */
	public ArrayList <String> setAlienName (){
		ArrayList <String> list = new ArrayList <String>();
		for( AlienName e : AlienName.values()){
			list.add(e.getAlienName());
		}
		Collections.shuffle(list);
		return list;
	}
	
	
	/**This method adds in a list, the enum values containing the players' names. This list is passed as a parameter when
	 * the characters are created since the character's constructor needs a string name as parameter.
	 * @return The list of human names.
	 */
	private ArrayList <String> setHumanName (){
		ArrayList <String> list = new ArrayList <String>();
		for( HumanName e : HumanName.values()){
			list.add(e.getHumanName());
		}
		Collections.shuffle(list);
		return list;
	}
	
	
	
	
	/**
	 * This method associates a different character to each player's ID, so that there's no way to confuse the players.
	 * The method uses setAlienName and SetHumanName to get the characters' names needed to be passed to the character's constructor.
	 * @param IDsofplayers : IDs of players that joined the game; this is given by the controller when a player sends the request and goes into the waiting list.
	 * @return List of the characters that are associated to the different Plsyers' ID.
	 */
	private ArrayList <Character> createCharacterList(ArrayList <Integer> IDsofplayers){

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
			map.takeSector("HUMAN_START").getCharacterList().add(human);
		}
		for(int j=0; j<alienNumber; j++){
			Alien alien=new Alien(alienList.remove(alienList.size()-1),IDsofplayers.remove(IDsofplayers.size()-1));
			alien.setCurrentSector(map.takeSector("ALIEN_START"));
			characterList.add(alien);
			map.takeSector("ALIEN_START").getCharacterList().add(alien);
		}
		Collections.shuffle(characterList);
		return characterList;
	}

	public Map getMap() {
		return map;
	}
	
	
	//GETTERS AND SETTERS
	
	public EscapeHatchDeck getEscapeHatchDeck() {
		return escapeHatchDeck;
	}
	
	public ArrayList<Character> getCharacterList() {
		return characterList;
	}


	public void setEscapeHatchDeck(EscapeHatchDeck escapeHatchDeck) {
		this.escapeHatchDeck = escapeHatchDeck;
	}


	public GameDeck getGameDeck() {
		return gameDeck;
	}


	public void setGameDeck(GameDeck gameDeck) {
		this.gameDeck = gameDeck;
	}


	public void setRound(int round) {
		this.round = round;
	}


	public void setCharacterList(ArrayList<Character> characterList) {
		this.characterList = characterList;
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


	public Character getCurrentCharacter() {
		return currentCharacter;
	}
	
	

	public void setCurrentCharacter(Character currentCharacter) {
		this.currentCharacter = currentCharacter;
	}

	
	// rimescolo il mazzo
	public GameDeck setGameDeck(){
		return this.gameDeck=new GameDeck();
		
	}
	
	
	public void goToNextCharacter(){
		if(getCharacterList().indexOf(getCurrentCharacter())<getCharacterList().size()-1)
			setCurrentCharacter(getCharacterList().get(getCharacterList().indexOf(getCurrentCharacter())+1));
		
		else {
			if(round>=MAX_NUM_ROUND)
				System.out.println("IL MATCH E TERMINATO IN QUANTO RAGGIUNTO IL NUMERO MASSIMO DI ROUND = " +MAX_NUM_ROUND);
			else{
				round = round+1;
				System.out.println("\nThe turn of the last player's ended, it's starting the round number: "+round);
				currentCharacter = getCharacterList().get(0);
			}
			
		}
		
	}


	public Turn getTurn() {
		return turn;
	}


	public void setTurn(Turn turn) {
		this.turn = turn;
	}
	
	public void currentCaracterDrawsItemCard(){
		if(getCurrentCharacter().getItemPlayerCard().size()<3)
			getCurrentCharacter().getItemPlayerCard().add(getItemDeck().removeCard());
		else
			System.out.println("You can hold only 3 item cards, so you must use one before drawing");
		
	}
	
	public String reachableSectorsOfTheCurrentCharacter(Character character){
		return getMap().takeSector(character.getCurrentSector()
				.getSectorName()).getReachableSectors(character.getMaxMove(), character.getCurrentSector()).toString();
	}
	
	//zhou metodo per ottenere il numero di giocatori umani presenti al gioco
	public int getNumberOfHumanAlive(){
		int numberOfHumanAlive=0;
		for(Character character : this.characterList){
			if(character.getClass()==Human.class)
				numberOfHumanAlive++;
		}
		
		return numberOfHumanAlive;
		
		
	}
	
	
	
}
	
