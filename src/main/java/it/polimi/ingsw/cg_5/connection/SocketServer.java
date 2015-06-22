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
	protected SocketServer(int port)  {
		super();
		instance = new SocketServer(7777);
		try {
			System.out.println("debug1");
            this.serverSocket = new ServerSocket(port); //prova a spostare broker thread in game manager !
            System.out.println("Server ready");
            this.brokerSocket = new ServerSocket(1039);
            System.out.println("debug2");
            do {
            	System.out.println("debug3");
                Socket socket = serverSocket.accept();
                System.out.println("debug4");
                new ClientHandler(new SocketCommunicator(socket)).start();
                System.out.println("debug5");
                
                Socket broker = brokerSocket.accept();
                System.out.println("debug6");
                this.brokerThread = new BrokerThread(broker);
                System.out.println("debug7");
                brokerThread.start();
                System.out.println("debug8");
            }while (isStopped());
            serverSocket.close();
            brokerSocket.close();
       } catch (IOException ex) {
            throw new AssertionError("I/O Error occured!", ex);
        }
	}
	
	public static SocketServer getInstance() throws IOException{
		return instance;
	}
	
	public BrokerThread getBrokerThread(){
		return this.brokerThread;
	}
	
		

	private boolean isStopped() {
		return true;
	}



}