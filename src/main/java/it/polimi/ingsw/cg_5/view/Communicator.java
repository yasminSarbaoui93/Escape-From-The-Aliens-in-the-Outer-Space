package it.polimi.ingsw.cg_5.view;

import java.io.IOException;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;


public interface Communicator {
	void send(String msg);
	void sendDTO(PlayerDTO playerDTO) throws IOException;
	String receive();
	PlayerDTO receiveDTO() throws ClassNotFoundException, IOException;
	void close();
}
