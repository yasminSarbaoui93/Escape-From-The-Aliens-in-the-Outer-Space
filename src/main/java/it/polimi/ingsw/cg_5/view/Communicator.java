package it.polimi.ingsw.cg_5.view;


public interface Communicator {
	void send(String msg);
	String receive();
	void close();
}
