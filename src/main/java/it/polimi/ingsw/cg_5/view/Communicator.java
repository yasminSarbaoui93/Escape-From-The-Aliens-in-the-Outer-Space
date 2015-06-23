package it.polimi.ingsw.cg_5.view;

import java.io.IOException;
import java.util.NoSuchElementException;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;


public interface Communicator {
	/**Sends the input message to the specific socket connected to a port
	 * @param msg
	 */
	void send(String msg);
	/**Sends the object DTO to the specific port
	 * @param playerDTO
	 * @throws IOException
	 */
	void sendDTO(PlayerDTO playerDTO) throws IOException;
	/**Listens on a port waiting to get the message sent by the socket
	 * @return the message sent by the server or the client
	 * @throws NoSuchElementException
	 */
	String receive();
	/**Receives the object DTO
	 * @return PlayerDTO
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	PlayerDTO receiveDTO() throws Exception;
	void close();
}
