package it.polimi.ingsw.cg_5.connection.broker;
import it.polimi.ingsw.cg_5.model.Character;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BrokerThread extends Thread implements PubSubCommunication {

	private Socket socket; //socket verso lo specifico subscriber
	private PrintWriter out;//Message that the broker sends to the subscriber
	private ConcurrentLinkedQueue<String> buffer; //Queue of messages for each subscriber
	
	//Ogni broker thread è riferito ad uno specifico subscriber
	public BrokerThread(Socket socket) throws IOException{
		this.socket = socket;
		buffer = new ConcurrentLinkedQueue<String>();	
		//sends the message directly to the channel to which the socket is associated
		this.out = new PrintWriter(socket.getOutputStream());
		
	
	}
	@Override 
	public void run(){
		
		
		String msg = buffer.poll();
		System.out.println("PROVA A VEDERE SE LA PUBLISH FUNZIONA1" +msg);
		if(msg!=null){
			send(msg);
		System.out.println("PROVA A VEDERE SE LA PUBLISH FUNZIONA" +msg);}
		else{
			synchronized(buffer){
				try {
					buffer.wait();
				} catch (InterruptedException e) {
				}
			}
		}
		
	}
	@Override
	public void dispatchMessage(String msg,Boolean chat){

		buffer.add(msg);
		send(msg);
		synchronized(buffer){
			buffer.notify();
		}
	}
	@Override
	public void updateNumberGame(Integer numberGame){
		buffer.add(numberGame.toString());
		send(numberGame.toString());
		synchronized(buffer){
			buffer.notify();
			System.out.println("number game nel buffer" + numberGame);
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
		
	}
	@Override
	public void updatecurrentPlayerId(int playerId) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
