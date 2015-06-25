package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface Client {

	
	/**Method on the client side that is called to send the request of accessing a new game.
	 * @param stringa
	 * @param maxSize
	 * @param name
	 * @param connectionType
	 * @return Integer corresponging the ID of the player.
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Integer matchRequest(String stringa, Integer maxSize, String name, String connectionType) throws Exception;
	
	/**Method on the client side to forward the request for moving onto a different sector.
	 * @param sector
	 * @param yourId
	 * @param gameNumber
	 * @return PlayerDTO of the current player
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PlayerDTO moveRequest(String sector, Integer yourId, Integer gameNumber) throws Exception;
	
	/**Method on the client side to forward the request for performing an attack on the current sector.
	 * @param yourId
	 * @param gameNumber
	 * @return PlayerDTO of the current player
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PlayerDTO attackRequest(Integer yourId, Integer gameNumber) throws Exception;
	
	/**Method on the client side to forward the request for ending the turn.
	 * @param yourId
	 * @param gameNumber
	 * @return PlayerDTO of the current player
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PlayerDTO endTurnRequest(Integer yourId, Integer gameNumber) throws Exception;
	
	/**Method on the client side to forward the request for drawing a card from the game deck.
	 * @param yourId
	 * @param gameNumber
	 * @return PlayerDTO of the current player.
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PlayerDTO drawCardRequest(Integer yourId, Integer gameNumber) throws Exception;
	
	/**Method on the client side to forward the request for using a card from the item cards owned by the player.
	 * @param itemCardType
	 * @param yourId
	 * @param gameNumber
	 * @return PlayerDTO of the current player
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PlayerDTO useCardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws Exception;
	
	/**Method on the client side to forward the request for using the spotlight item card.
	 * @param itemCardType
	 * @param yourId
	 * @param gameNumber
	 * @param sector
	 * @return PlayerDTO of the current player
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PlayerDTO useSpotLightRequest(String itemCardType, Integer yourId, Integer gameNumber,String sector) throws Exception;
	
	/**Method on the client side to forward the request for bluffing the position after the player's drawn the game card "Noise in any sector".
	 * @param bluffSector
	 * @param yourId
	 * @param gameNumber
	 * @return PlayerDTO of the current player
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PlayerDTO bluffRequest(String bluffSector, Integer yourId, Integer gameNumber) throws Exception;
	
	/**Method on the client side to forward the request for discarding a card from the item cards owned by the player.
	 * @param itemCardType
	 * @param yourId
	 * @param gameNumber
	 * @return PlayerDTO of the current player
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PlayerDTO discardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws Exception;

	/**Method on the client side to forward the request for sending a message on the chat panel.
	 * @param message
	 * @param yourId
	 * @param gameNumber
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void sendmessageRequest(String message,Integer yourId, Integer gameNumber) throws Exception;
}
