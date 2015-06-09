package it.polimi.ingsw.cg_5.connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BrokerInterface extends Remote {

	public void subscribe(SubscriberInterface r) throws RemoteException;
	
}
