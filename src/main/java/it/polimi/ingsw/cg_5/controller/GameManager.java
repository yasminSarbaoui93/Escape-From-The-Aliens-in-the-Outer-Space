package it.polimi.ingsw.cg_5.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Timer;

import it.polimi.ingsw.cg_5.connection.GameRules;
import it.polimi.ingsw.cg_5.connection.broker.Broker;
import it.polimi.ingsw.cg_5.connection.broker.BrokerRmi;
import it.polimi.ingsw.cg_5.connection.broker.BrokerSocket;
import it.polimi.ingsw.cg_5.connection.broker.PubSubCommunication;
import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.view.User;


public class GameManager implements Observer{
	private static GameManager instance=null;
	private static Integer indexOfCurrentMatches=0;
	private HashMap <Integer , Match> listOfMatch= new HashMap <Integer, Match> () ;
	private PlayerListManager playerListManager =new PlayerListManager();
	private GameRules gameRules = new GameRules(this);
		

	private GameManager(){
		
	}
	
	public static GameManager getInstance(){
		if(instance == null){
			instance = new GameManager();
		}return instance;
	}
	
	/**Method that creates a new match of the game. The conditions to respect are mainly two: the waiting list of a certain game is full; the timer reaches the maximum waiting time set.
	 * @throws Exception 
	 * @throws RemoteException 
	 * 
	 */
	public void MatchCreator(String connectionType){
		ArrayList <WaitingList> waitingListToRemove= new ArrayList <WaitingList>() ;
		
		for(WaitingList waitingList : playerListManager.getWaitingLists()){
			if(waitingList.canStartNewGame()){
				ArrayList <Integer> lista = waitingList.getPlayersID();
				GameState newGameState=new GameState(lista,waitingList.getChoosenMap(),indexOfCurrentMatches);
				newGameState.addObserver(this);
				Broker matchBroker;
				if(connectionType.toUpperCase().equals("RMI"))
					matchBroker = new BrokerRmi(indexOfCurrentMatches.toString());
				else{
					matchBroker = new BrokerSocket(indexOfCurrentMatches.toString());
				}
				
				for ( PubSubCommunication subscriber : waitingList.getPlayersSubscriber()){
						matchBroker.subscribe(subscriber);
				}				
				Match newMatch =new Match(newGameState ,indexOfCurrentMatches,matchBroker);
				newMatch.getBroker().publish(false, "You've been added to the game number "+indexOfCurrentMatches);
				newMatch.getBroker().publishNumberGame(indexOfCurrentMatches,newMatch.getGameState().getCurrentCharacter().getPlayerID());
				
				
				for(User user : waitingList.getUsers()){
					for (Character character : newGameState.getCharacterList()){
						if(user.getPlayerId()==character.getPlayerID()){
							
							try {
								user.getUserSubscriber().updateCharacter(character);
							} catch (Exception e) {
								new Exception("Impossible to update character");
							}
						}
					}

				}
				newMatch.getBroker().publish(false, "The Player with ID- "+newMatch.getGameState().getCurrentCharacter().getPlayerID()
						+"start to play!");
				newGameState.getTimer().cancel();
				newGameState.getTimer().purge();
				taskTimer task= new taskTimer(newMatch);
				newGameState.setTimer(new Timer());
				newGameState.getTimer().schedule(task, 120*1000);

				
				newGameState.getMap().drawMap();

				listOfMatch.put(indexOfCurrentMatches,newMatch);
				waitingListToRemove.add(waitingList);
				System.out.println("Ho Creato un nuovo Match");
				indexOfCurrentMatches++;
			}
		}
		playerListManager.getWaitingLists().removeAll(waitingListToRemove); // rimuovo tutte le liste di giochi partiti
		
				
	}

	public HashMap<Integer,Match> getListOfMatch() {
		return listOfMatch;
	}
	
	public Match takeMatch(int numberGame){
		return listOfMatch.get(numberGame);
	}
	
	

	public PlayerListManager getPlayerListManager() {
		return playerListManager;
	}
	
	
	/**Controls if the player that sends a request for doing a certain action is in the list of player of the match and if it's his turn.
	 * @param numberGame
	 * @param playerID
	 * @return true if the player's ID is in the list of current players of a certain match.
	 */
	public boolean canAct(Integer numberGame,Integer playerID){
		if(this.listOfMatch.containsKey(numberGame)){
				if(this.listOfMatch.get(numberGame).getGameState().getCurrentCharacter().getPlayerID().equals(playerID))
					return true;		
			}
	
		return false;
		
	}
	

	@Override
	public void update(Observable o, Object arg) {

		if(arg instanceof Character){
			Character character = (Character) arg;
			System.out.println(character);
		}
		
		if(arg instanceof String){
			String message = (String) arg;
			Scanner in = new Scanner(message);
			Integer gameNumber=Integer.parseInt(in.next());
			try {
				this.listOfMatch.get(gameNumber).getBroker().publish(false, in.nextLine());
			}catch (Exception e) {
				new Exception("There's no message to be sent by the broker!");
			}
			in.close();
			
		}

		
	}

	public GameRules getGameRules() {
		return gameRules;
	}

	public void setGameRules(GameRules gameRules) {
		this.gameRules = gameRules;
	}
	
	
	
	
}
