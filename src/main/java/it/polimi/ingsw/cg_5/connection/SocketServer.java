package it.polimi.ingsw.cg_5.connection;


import it.polimi.ingsw.cg_5.connection.broker.BrokerThread;
import it.polimi.ingsw.cg_5.controller.GameManager;
import it.polimi.ingsw.cg_5.view.SocketCommunicator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Server {


	private static SocketServer instance=null;

	private BrokerThread brokerThread;
	
	private ServerSocket serverSocket; 
	private ServerSocket brokerSocket;

	protected SocketServer()  {

		super();

	}
	
	public void run(){

		try {
            this.serverSocket = new ServerSocket(7777); 
            System.out.println("Server ready");
            this.brokerSocket = new ServerSocket(1040);
            do {
                Socket socket = serverSocket.accept();
                new ClientHandler(new SocketCommunicator(socket)).start();
                
                Socket broker = brokerSocket.accept();
                this.brokerThread = new BrokerThread(broker);
             //   brokerThread.start();
            }while (isStopped());
            	serverSocket.close();
            	brokerSocket.close();
       } catch (IOException ex) {
            throw new AssertionError("I/O Error occured!", ex);
        }
	}
	
	public static SocketServer getInstance() throws IOException{

		if(instance == null){
			instance = new SocketServer();
		}return instance;

	}
	
	public BrokerThread getBrokerThread(){
		return this.brokerThread;
	}
	
		

	private boolean isStopped() {
		return true;
	}



}
