package it.polimi.ingsw.cg_5.controller.connections;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private String ip;
    private int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
	}
	
	public void startClient() {
		try {
            String command = "";
            Scanner stdin = new Scanner(System.in);
            Socket socket = new Socket(ip, port);
            SocketCommunicator server = new SocketCommunicator(socket);
            System.out.println("Connection established");

            do {
                command = stdin.nextLine();
                server.send(command);
                String response = server.receive();
                System.out.println(response);

            } while (!command.equals("exit"));

            server.close();
            stdin.close();
        } catch (IOException ex) {
            throw new AssertionError("Weird errors with I/O occured, please verify environment config", ex);
        }
		
	}
	
	
	public static void main(String[] args) { 
	    Client client = new Client("127.0.0.1", 1337);
        client.startClient();
    }
	
}
	
