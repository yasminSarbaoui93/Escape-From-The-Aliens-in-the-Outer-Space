package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;


public interface Communicator {
	void send(String msg);
	void sendDTO(PlayerDTO playerDTO);
	String receive();
	PlayerDTO receiveDTO();
	void close();
}
