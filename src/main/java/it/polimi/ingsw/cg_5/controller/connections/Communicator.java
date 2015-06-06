package it.polimi.ingsw.cg_5.controller.connections;

public interface Communicator {

	void send(String msg);
	String receive();
	void close();
	
}