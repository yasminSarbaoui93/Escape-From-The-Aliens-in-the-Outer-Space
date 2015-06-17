package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketCommunicator implements Communicator{
	PlayerDTO playerDTO;
	
	Socket socket;
	
	//Leggere input da socket
	Scanner in;
	ObjectInputStream inObj;
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
		out.flush();
	}
	
    @Override
	public String receive(){
		return in.nextLine().toString();
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

	@Override
	public void sendDTO(PlayerDTO playerDTO) {
		out.println(playerDTO);
		out.flush();
	}

	@Override
	public PlayerDTO receiveDTO() {
		try {
			return (PlayerDTO)inObj.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerDTO;
	}
	

}
