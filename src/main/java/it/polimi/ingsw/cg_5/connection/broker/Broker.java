package it.polimi.ingsw.cg_5.connection.broker;


import java.rmi.RemoteException;

public interface Broker{
	void publish(String msg)throws RemoteException;
	void setTopic(String topic);
	void publishNumberGame(Integer numberGame, int playerId) throws RemoteException;
	void subscribe(PubSubCommunication o);
}
