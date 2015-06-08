package PubSub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubscriberInterface extends Remote {
	
	public void dispatchMessage(String msg) throws RemoteException;

}
