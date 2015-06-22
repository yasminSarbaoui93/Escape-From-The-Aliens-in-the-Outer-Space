package it.polimi.ingsw.cg_5.connection;

import java.io.IOException;
import java.rmi.RemoteException;

public class MainServer {
	


	public static void main(String [] args) throws IOException{
		Server server1 = new RmiServer();
		Server server2 = SocketServer.getInstance();
	//	Server server2 = new SocketServer(6527);
	}
	

}
