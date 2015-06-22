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
	
	private PlayerDTO receivePlayerDTO(String command) throws ClassNotFoundException, IOException{
		server.send(command);
		PlayerDTO playerDTO;
		playerDTO = server.receiveDTO();
		return playerDTO;
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
		return receivePlayerDTO(command);
	}

	@Override
	public PlayerDTO attackRequest(Integer yourId, Integer gameNumber) throws ClassNotFoundException, IOException {
		String command = "ATTACK "+yourId+" "+gameNumber;
		return receivePlayerDTO(command);
	}

	@Override
	public PlayerDTO endTurnRequest(Integer yourId, Integer gameNumber) throws ClassNotFoundException, IOException {
		String command = "END_TURN "+yourId+" "+gameNumber;
		return receivePlayerDTO(command);
	}

	@Override
	public PlayerDTO drawCardRequest(Integer yourId, Integer gameNumber) throws ClassNotFoundException, IOException {
		String command = "DRAW "+yourId+" "+gameNumber;
		return receivePlayerDTO(command);
	}

	@Override
	public PlayerDTO useCardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws ClassNotFoundException, IOException {
		String command = "USE_CARD "+itemCardType+" "+yourId+" "+gameNumber;
		return receivePlayerDTO(command);
	}

	@Override
	public PlayerDTO useSpotLightRequest(String itemCardType, Integer yourId, Integer gameNumber, String sector) throws ClassNotFoundException, IOException {
		String command = "SPOT "+itemCardType+" "+yourId+" "+gameNumber+" "+sector;
		return receivePlayerDTO(command);
	}

	@Override
	public PlayerDTO bluffRequest(String bluffSector, Integer yourId, Integer gameNumber) throws ClassNotFoundException, IOException {
		String command = "BLUFF "+bluffSector+" "+yourId+" "+gameNumber;
		return receivePlayerDTO(command);
	}

	@Override
	public PlayerDTO discardRequest(String itemCardType, Integer yourId, Integer gameNumber) throws ClassNotFoundException, IOException {
		String command = "DISCARD "+itemCardType+" "+yourId+" "+gameNumber;
		return receivePlayerDTO(command);
	}

	@Override
	public Void sendmessageRequest(String message, Integer yourId,
			Integer gameNumber) throws RemoteException, ClassNotFoundException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
