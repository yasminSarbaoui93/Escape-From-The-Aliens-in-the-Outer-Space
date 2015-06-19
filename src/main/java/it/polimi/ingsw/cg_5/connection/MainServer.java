package it.polimi.ingsw.cg_5.connection;

import java.rmi.RemoteException;

public class MainServer {


	public static void main(String [] args) throws RemoteException{
		Server server1 = new RmiServer();
		Server server2 = new SocketServer(1337);
		
	}
	

}
