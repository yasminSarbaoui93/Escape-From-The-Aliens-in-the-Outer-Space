package it.polimi.ingsw.cg_5.connection.broker;


import java.rmi.RemoteException;

public interface Broker{
	/**Takes as input the message that has to be sent broadcast by the broker to all the subscribers and pushes it into the connection channel with a dispatch message.
	 * @param msg to be sent to all the subscribers
	 * @param chat boolean to check if it's a chat message a command message.
	 * @throws RemoteException
	 */
	void publish(Boolean chat, String msg)throws RemoteException;
	/**Changes the topic of the Broker to the number of the game that it is associated to.
	 * @param topic
	 */
	void setTopic(String topic);
	
	/**This is the first publish to be done once the game starts; it publishes the number of the game that the subscribers are subscribed to.
	 * @param numberGame
	 * @param playerId
	 * @throws RemoteException
	 */
	void publishNumberGame(Integer numberGame, Integer playerId) throws RemoteException;
	
	/**Associates all the subscribers to the right broker, in this case putting them into an Array List of subscribers (or BrokerThreads).
	 * @param o
	 */
	void subscribe(PubSubCommunication o);
}
