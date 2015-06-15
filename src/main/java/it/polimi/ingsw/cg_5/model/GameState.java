package it.polimi.ingsw.cg_5.model;


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
	private final Integer matchIndex;
	
	
	
	
	/**
	 * Costructor of a basic Game state; the map initalized is based on the string passed as a parameter.
	 * All the decks are initialized and the characters are assigned casually to each player
	 * @param IDsofplayers: list of all the players that joined the game (created by the controller)
	 * @param choosenMap: map where the players will start the game
	 */
	public GameState(ArrayList <Integer> IDsofplayers , String choosenMap, Integer matchIndex ){
		
		map=new Map(choosenMap);
		round=1;
		escapeHatchDeck= new EscapeHatchDeck();
		itemDeck= new ItemDeck();
		gameDeck=new GameDeck();
		characterList=createCharacterList(IDsofplayers);
		this.MAX_NUM_ROUND = 39;
		currentCharacter=getCharacterList().get(0);
		turn.setTurnState(TurnState.STARTED);
		this.matchIndex=matchIndex;
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
	
	
	/*****************************GETTERS AND SETTERS**********************************/
	
	
	public EscapeHatchDeck getEscapeHatchDeck() {
		return escapeHatchDeck;
	}
	
	public Integer getMatchIndex() {
		return matchIndex;
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


	public void setRound(int round) {
		this.round = round;
		this.setChanged();
		notifyObservers(this.matchIndex +" The turn of the last player's ended, it's starting the round number: "+round);
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
		this.setChanged();
		notifyObservers(this.matchIndex +" "+attackedCharacter +" and i'm not in the game anymore.");

	}
	
	public int getRound() {
		return round;
	}


	public Character getCurrentCharacter() {
		return currentCharacter;
	}
	
	public void destroyShallop(Sector destinationSector){
		((EscapeSector)getMap().takeSector(destinationSector.getSectorName())).setAvailable(false);
		this.setChanged();
		notifyObservers(this.matchIndex+" The shallop "+ destinationSector+" were destroyed by the player "+ getCurrentCharacter().getPlayerID());
	}
	

	public void setCurrentCharacter(Character currentCharacter) {
		this.currentCharacter = currentCharacter;
		this.setChanged();
		notifyObservers();
	}
	
	public void setCurrentSectorOfCurrentCharacter(Sector currentSector){
		this.currentCharacter.setCurrentSector(currentSector);
		this.setChanged();
		notifyObservers(this.matchIndex +" The player with ID-"+getCurrentCharacter().getPlayerID()+" has moved.");
	}
	

	
	// rimescolo il mazzo
	public GameDeck setGameDeck(){
		this.gameDeck=new GameDeck();
		this.setChanged();
		notifyObservers("The cards of the game deck are over. A new one has been created");
		return this.gameDeck;
	}
	
	
	public void goToNextCharacter(){
		
		if(getCharacterList().indexOf(getCurrentCharacter())<getCharacterList().size()-1){
			setCurrentCharacter(getCharacterList().get(getCharacterList().indexOf(getCurrentCharacter())+1));
			setChanged();
			notifyObservers(this.matchIndex+" Is the turn of the Player: "+currentCharacter.getPlayerID());
		}
		else {
				setRound(round+1);
				currentCharacter = getCharacterList().get(0);
				setChanged();
				notifyObservers(this.matchIndex+" Is the turn of the Player: "+currentCharacter.getPlayerID());
			}
			
		
		
		

		
		//System.out.println("It's the turn of the player with the ID-"+currentCharacter.getPlayerID());

}


	public Turn getTurn() {
		return turn;
	}


	public void setTurn(Turn turn) {
		this.turn = turn;
	}
	
	public Card currentCharacterDrawsItemCard(){
		//if(getCurrentCharacter().getItemPlayerCard().size()<3)
			ItemCard DrawItemCard = getItemDeck().removeCard();
			getCurrentCharacter().getItemPlayerCard().add(DrawItemCard);
		//else
		//	System.out.println("You can hold only 3 item cards, so you must use one before drawing");
		setChanged();
		notifyObservers(this.matchIndex+" The player "+currentCharacter.getPlayerID()+" drew an item card.");
		return DrawItemCard;
	}
	
	public Card currentCharacterDrawsGameCard(){
		Card card = gameDeck.removeCard();
		this.setChanged();
		notifyObservers(this.matchIndex+" The Player "+currentCharacter.getPlayerID()+" drew a game card.");
		return card;
		
		
	}
	
	public String reachableSectorsOfTheCurrentCharacter(Character character){
		return getMap().takeSector(character.getCurrentSector()
				.getSectorName()).getReachableSectors(character.getMaxMove(), character.getCurrentSector()).toString();
	}
	
	
	/**This method takes the list of current players of a certin match, and it's called after an attack or after a human reaches the Escape Hatch Sector to check the number of Humans still alive in the current game.
	 * @return list of humans still alive in the current game.
	 */
	public int getNumberOfHumanAlive(){
		int numberOfHumanAlive=0;
		for(Character character : this.characterList){
			if(character.getClass()==Human.class)
				numberOfHumanAlive++;
		}
		
		return numberOfHumanAlive;
		
		
	}
	
	/**This method takes the list of current players of a certin match, and it's called after an attack to check the number of Aliens still alive in the current game.
	 * @return list of aliens still alive in the current game.
	 */
	public int getNumberOfAliensAlive(){
		int numberOfAliensAlive = 0;
		for(Character character: this.characterList){
			if(character.getClass()==Alien.class)
				numberOfAliensAlive++;
		}
		
		return numberOfAliensAlive;
	}
	
	
	
}
	
