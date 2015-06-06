package it.polimi.ingsw.cg_5.controller.connections;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private int port;
	private ServerSocket serverSocket; 
	
	//Initializing a server based on the port where the client will access
	public Server(int port) {
		this.port = port; 	
	}
	
	public void startServer() {
		try {
			//socket that estabilish the connection on a certain port
            serverSocket = new ServerSocket(port);
            
            //Server's ready to wait for connections
            System.out.println("Server ready");
           
            while (isStopped()) {
            	//new socket created for each accepted connection
                Socket socket = serverSocket.accept();
                
                //creates a new process (thread) that will execute the run.
                //For escapeFromAliens no
                new ClientHandler(new SocketCommunicator(socket)).start();
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
		Server server = new Server(1337);
        server.startServer();
	}

}