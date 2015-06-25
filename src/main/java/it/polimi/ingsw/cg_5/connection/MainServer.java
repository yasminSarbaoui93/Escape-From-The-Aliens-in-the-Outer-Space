package it.polimi.ingsw.cg_5.connection;

import java.io.IOException;

public class MainServer {
	


	public static void main(String [] args) throws IOException{
		
		new RmiServer();
		SocketServer.getInstance().run();
	}
	

}
