package it.polimi.ingsw.cg_5.connection.broker;
import it.polimi.ingsw.cg_5.model.Character;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class BrokerThread implements PubSubCommunication {

	private Socket socket; 
	private PrintWriter out;
	private ObjectOutput outObj;
	public BrokerThread(Socket socket) throws IOException{
		this.socket = socket;
		this.out = new PrintWriter(socket.getOutputStream());
		this.outObj = new ObjectOutputStream(socket.getOutputStream());
		outObj.flush();
	}
	

	@Override
	public void dispatchMessage(Boolean chat, String msg){
		send(chat.toString()+" "+msg);
	}
	@Override
	public void updateNumberGame(Integer numberGame) throws InterruptedException{
		send("NUMBERGAME "+numberGame.toString());
	}
	
	@Override
	public void updatecurrentPlayerId(Integer playerId) {
		send("PLAYERID "+playerId.toString());
	}
	
	@Override
	public void updateCharacter(Character character) throws IOException, InterruptedException {
		send("CHARACTER ");
		synchronized(outObj){
			outObj.wait(5);
		}
		sendCharacter(character);
		
	}
	
	private void sendCharacter(Character character) throws IOException{
		outObj.writeObject(character);
		outObj.flush();
	}
	
	private void send(String msg){
		out.println(msg);
		out.flush();
	}
	
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
		} finally {
			out = null;
			socket = null;
			System.gc();
		}
	}
	
	

}
