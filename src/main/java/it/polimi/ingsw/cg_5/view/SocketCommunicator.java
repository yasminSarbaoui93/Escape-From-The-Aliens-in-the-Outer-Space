package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketCommunicator implements Communicator{
	PlayerDTO playerDTO;
	
	Socket socket;
	
	//Leggere input da socket
	Scanner in;
	
	//Scrivere su socket
	PrintWriter out;
	
	public SocketCommunicator(Socket socket){
		this.socket=socket;
		
		try{
		in = new Scanner(socket.getInputStream());
		out = new PrintWriter(socket.getOutputStream());
		}catch(IOException ex){
			throw new AssertionError("Configuration problem occured", ex);
		}
	}
	
	@Override
	public void send(String msg){
		out.println(msg);
		//makes sure that the message is updated/sent
		out.flush();
	}
	
    @Override
	public String receive(){
		return in.nextLine();
	}
	
    @Override
	public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("something wrong happened while closing a socket: " + e.getMessage());
        } finally {
        		socket = null;
        }
    }
	

}
