package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import org.junit.runners.ParentRunner;

public class SocketClient implements Client {
	
	private String ip;
	private int port;
	Socket socket;
	SocketCommunicator server;
	
	public SocketClient(String ip, int port) throws UnknownHostException, IOException{
		this.ip=ip;
		this.port=port;
		this.socket = new Socket (ip, port);
		this.server = new SocketCommunicator(socket);
		
	}
	


	@Override
	public Integer matchRequest(String stringa, Integer maxSize, String name, String connectionType) throws RemoteException, NotBoundException {
		
		String command = "SUBSCRIBEREQUEST "+stringa +" "+maxSize+" "+name+" "+connectionType;
		server.send(command);
		String yourId = server.receive();
		Integer yourID = Integer.parseInt(yourId);
		return yourID;
	}

	
	
	@Override
	public PlayerDTO moveRequest(String sector, Integer yourId, Integer gameNumber) throws ClassNotFoundException, IOException {
		String command = "MOVE "+sector+" "+yourId+" "+gameNumber;
		server.send(command);
		PlayerDTO playerDTO;
		playerDTO = server.receiveDTO();
		return playerDTO;
	}

	@Override
	public PlayerDTO attackRequest(Integer yourId, Integer gameNumber) throws RemoteException {
		String command = "ATTACK"+yourId+" "+gameNumber;
		server.send(command);
		return null;
	}

	@Override
	public PlayerDTO endTurnRequest(Integer yourId, Integer gameNumber) throws RemoteException {

		return null;
	}

	@Override
	public PlayerDTO drawCardRequest(Integer yourId, Integer gameNumber) throws RemoteException {

		return null;
	}

	@Override
	public PlayerDTO useCardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws RemoteException {

		return null;
	}

	@Override
	public PlayerDTO useSpotLightRequest(String itemCardType, Integer yourId, Integer gameNumber, String sector) throws RemoteException {
		
		return null;
	}

	@Override
	public PlayerDTO bluffRequest(String bluffSector, Integer yourId, Integer gameNumber) throws RemoteException {

		return null;
	}

	@Override
	public PlayerDTO discardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws RemoteException {

		return null;
	}

}
