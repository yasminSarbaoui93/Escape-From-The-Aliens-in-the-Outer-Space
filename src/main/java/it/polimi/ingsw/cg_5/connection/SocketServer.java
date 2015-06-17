package it.polimi.ingsw.cg_5.connection;

import it.polimi.ingsw.cg_5.view.SocketCommunicator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Server {
	

	private ServerSocket serverSocket; 
	
	public SocketServer(int port) {
		
		try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server ready");
            RemoteMethodsImpl remoteMethods1 = new RemoteMethodsImpl(gameManager);
            while (isStopped()) {
                Socket socket = serverSocket.accept();
                new ClientHandler(new SocketCommunicator(socket), gameManager, remoteMethods1).start();
            }
            serverSocket.close();
        } catch (IOException ex) {
            throw new AssertionError("Weird errors with I/O occured, please verify environment config", ex);
        }
	}
		

	private boolean isStopped() {
		return true;
	}

	public static void main(String[] args) { 
		new SocketServer(1337);
	}

}