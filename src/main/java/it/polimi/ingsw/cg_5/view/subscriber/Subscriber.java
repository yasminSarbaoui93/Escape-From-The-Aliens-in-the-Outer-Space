package it.polimi.ingsw.cg_5.view.subscriber;

import java.rmi.RemoteException;

import it.polimi.ingsw.cg_5.view.View;

public interface Subscriber  {

	void setView(View view) throws RemoteException;

	
}
