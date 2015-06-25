package it.polimi.ingsw.cg_5.connection.broker;
import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.model.Character;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BrokerThread /*extends Thread */implements PubSubCommunication {

	private Socket socket; 
	private PrintWriter out;
	private ConcurrentLinkedQueue<String> buffer;
	private ObjectOutput outObj;
	public BrokerThread(Socket socket) throws IOException{
		this.socket = socket;
		buffer = new ConcurrentLinkedQueue<String>();	
		this.out = new PrintWriter(socket.getOutputStream());
		this.outObj = new ObjectOutputStream(socket.getOutputStream());
		outObj.flush();
	}
	
/*	@Override 
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
	//}*/
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.cg_5.connection.broker.PubSubCommunication#dispatchMessage(java.lang.String, java.lang.Boolean)
	 *Adds the message to the buffer that is sleeping and wakes up the buffer to poll the message.
	 */
	@Override
	public void dispatchMessage(Boolean chat, String msg){
		//buffer.add(chat.toString()+msg);
		send(chat.toString()+" "+msg);
	//	synchronized(buffer){
		//	buffer.notify();
		//}
	}
	@Override
	public void updateNumberGame(Integer numberGame) throws InterruptedException{
//		buffer.add(numberGame.toString());
		send("NUMBERGAME "+numberGame.toString());
	//	synchronized(buffer){
		///	buffer.notify();
		//}
	}
	
	@Override
	public void updatecurrentPlayerId(Integer playerId) {
	//	buffer.add(playerId.toString());
		send("PLAYERID "+playerId.toString());
//		synchronized(buffer){
	//		buffer.notify();
		//}		
	}
	
	@Override
	public void updateCharacter(Character character) throws IOException, InterruptedException {
		send("CHARACTER ");
		//synchronized(out){
		//out.wait();}
		//out.wait(5);
		//Character playerDTO = new PlayerDTO(character);
		sendCharacter(character);
		
		//synchronized(out){
		//out.notify();}
		//buffer.add("CHARACTER");
		/*synchronized(buffer){
			buffer.notify();
			System.out.println("CHARACTER nel buffer" + character);
		}*/
		//outObj.close();
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
