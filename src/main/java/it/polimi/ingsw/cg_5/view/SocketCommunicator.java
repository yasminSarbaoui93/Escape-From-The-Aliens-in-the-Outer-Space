package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketCommunicator implements Communicator{
	PlayerDTO playerDTO;
	
	Socket socket;
	
	//Leggere input da socket
	Scanner in;
	ObjectInput inObj;
	ObjectOutput outObj;
	//Scrivere su socket
	PrintWriter out;
	
	public SocketCommunicator(Socket socket) throws IOException{
		this.socket=socket;
		
		in = new Scanner(socket.getInputStream());
		out = new PrintWriter(socket.getOutputStream());

	}
	
	@Override
	public void send(String msg){
		out.println(msg);
		out.flush();
	}
	
    @Override
	public String receive() throws NoSuchElementException{
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

	@Override
	public void sendDTO(PlayerDTO playerDTO) throws IOException {
			if(playerDTO == null) throw new IOException("Troubles sending the player's data transfer object");
			outObj = new ObjectOutputStream(socket.getOutputStream());
			outObj.writeObject(playerDTO);;
			outObj.flush();
	}

	@Override
	public PlayerDTO receiveDTO() throws ClassNotFoundException, IOException {
		inObj = new ObjectInputStream(socket.getInputStream());
		Object object = inObj.readObject();
		playerDTO = (PlayerDTO)object;
		return playerDTO;
	}
	

}
