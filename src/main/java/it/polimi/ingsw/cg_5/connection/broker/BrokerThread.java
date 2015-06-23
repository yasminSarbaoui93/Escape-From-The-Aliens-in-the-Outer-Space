package it.polimi.ingsw.cg_5.connection.broker;
import it.polimi.ingsw.cg_5.model.Character;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BrokerThread extends Thread implements PubSubCommunication {

	private Socket socket; 
	private PrintWriter out;
	private ConcurrentLinkedQueue<String> buffer;
	
	public BrokerThread(Socket socket) throws IOException{
		this.socket = socket;
		buffer = new ConcurrentLinkedQueue<String>();	
		this.out = new PrintWriter(socket.getOutputStream());
	}
	
	@Override 
	public void run(){		
		String msg = buffer.poll();
		System.out.println("PROVA A VEDERE SE LA PUBLISH FUNZIONA1" +msg);
		/*if(msg!=null){
			send(msg);
		System.out.println("PROVA A VEDERE SE LA PUBLISH FUNZIONA" +msg);}
		else{
			synchronized(buffer){
				try {
					buffer.wait();
				}catch (InterruptedException e) {}
			}
		}	*/	
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.cg_5.connection.broker.PubSubCommunication#dispatchMessage(java.lang.String, java.lang.Boolean)
	 *Adds the message to the buffer that is sleeping and wakes up the buffer to poll the message.
	 */
	@Override
	public void dispatchMessage(Boolean chat, String msg){
		buffer.add(chat.toString()+msg);
		send(chat.toString()+" "+msg);
		synchronized(buffer){
			buffer.notify();
		}
	}
	@Override
	public void updateNumberGame(Integer numberGame){
		buffer.add(numberGame.toString());
		send("NUMBERGAME "+numberGame.toString());
		synchronized(buffer){
			buffer.notify();
			System.out.println("number game nel buffer" + numberGame);
		}
	}
	
	@Override
	public void updatecurrentPlayerId(Integer playerId) {
		buffer.add(playerId.toString());
		send("PLAYERID "+playerId.toString());
		synchronized(buffer){
			buffer.notify();
			System.out.println("player id nel buffer" + playerId);
		}
		
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
	@Override
	public void updateCharacter(Character character) {
		//da completare
	}
	

}
