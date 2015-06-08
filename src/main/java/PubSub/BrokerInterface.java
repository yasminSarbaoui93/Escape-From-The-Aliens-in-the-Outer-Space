package PubSub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BrokerInterface extends Remote {

	public void subscribe(SubscriberInterface r) throws RemoteException;
	
}
