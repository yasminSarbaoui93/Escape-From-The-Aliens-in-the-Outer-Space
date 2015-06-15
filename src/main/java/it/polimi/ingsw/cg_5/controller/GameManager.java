package it.polimi.ingsw.cg_5.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import it.polimi.ingsw.cg_5.connection.Broker;
import it.polimi.ingsw.cg_5.connection.SubscriberInterface;
import it.polimi.ingsw.cg_5.model.*;
import it.polimi.ingsw.cg_5.model.Character;
import it.polimi.ingsw.cg_5.view.User;




public class GameManager implements Observer{
	private static Integer indexOfCurrentMatches=0;
	private HashMap <Integer , Match> listOfMatch= new HashMap <Integer, Match> () ;
	private PlayerListManager playerListManager =new PlayerListManager();

	
	/**Method that creates a new match of the game. The conditions to respect are mainly two: the waiting list of a certain game is full; the timer reaches the maximum waiting time set.
	 * @throws RemoteException 
	 * 
	 */
	public void MatchCreator() throws RemoteException{
		ArrayList <WaitingList> waitingListToRemove= new ArrayList <WaitingList>() ;
		
		for(WaitingList waitingList : playerListManager.getWaitingLists()){
			if(waitingList.canStartNewGame()){
				ArrayList <Integer> lista = waitingList.getPlayersID();
				System.out.println(lista); //da togliere poi
				GameState newGameState=new GameState(lista,waitingList.getChoosenMap(),indexOfCurrentMatches);
				newGameState.addObserver(this);
				Broker matchBroker = new Broker(indexOfCurrentMatches.toString());
				
				for ( SubscriberInterface subscriber : waitingList.getPlayersSubscriber()){
					matchBroker.subscribe(subscriber);
				}				
				Match newMatch =new Match(newGameState ,indexOfCurrentMatches,matchBroker);
				
				
				newMatch.getBroker().publish("You've been added to the game number "+indexOfCurrentMatches);
				newMatch.getBroker().publishNumberGame(indexOfCurrentMatches);
				for(User user : waitingList.getUsers()){
					for (Character character : newGameState.getCharacterList()){
						if(user.getPlayerId()==character.getPlayerID()){
							user.getUserSubscriber().updateCharacter(character);
						}
					}
				}
				
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
				if(this.listOfMatch.get(numberGame).getGameState().getCurrentCharacter().getPlayerID()==playerID)
					return true;		
			}
	
		return false;
		
	}
	

	@Override
	public void update(Observable o, Object arg) {

		//This updates all the arguments of the type character that are returned from the methods attackCharacter or setCurrentCharacter of the model.
		if(arg instanceof Character){
			Character character = (Character) arg;
			System.out.println(character);
		}
		
		if(arg instanceof String){
			String message = (String) arg;
			Scanner in = new Scanner(message);
			Integer gameNumber=Integer.parseInt(in.next());
			this.listOfMatch.get(gameNumber).getBroker().publish(in.nextLine());
			in.close();
			
		}

		
	}
	
	
}
