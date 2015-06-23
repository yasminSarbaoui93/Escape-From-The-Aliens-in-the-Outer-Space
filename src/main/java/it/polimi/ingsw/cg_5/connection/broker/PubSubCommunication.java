package it.polimi.ingsw.cg_5.connection.broker;

import it.polimi.ingsw.cg_5.model.Character;

import java.rmi.RemoteException;

public interface PubSubCommunication {
	/**Sends the message given by the broker to the right subscriber (or Broker Thread)
	 * @param msg message to be sent
	 * @param chat boolean to check if the message belongs to the chat or to the game.
	 * @throws RemoteException
	 */
	public void dispatchMessage(Boolean chat, String msg) throws RemoteException;
	/**Once created the match, this is the first message to be sent and it's an information about the ID of the game to which the players belong.
	 * @param numberGame
	 * @throws RemoteException
	 */
	public void updateNumberGame(Integer numberGame) throws RemoteException;
	public void updateCharacter(Character character) throws IllegalArgumentException, RemoteException;
	public void updatecurrentPlayerId(int playerId) throws RemoteException;
}
