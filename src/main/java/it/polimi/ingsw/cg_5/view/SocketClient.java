package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

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
	public Integer matchRequest(String stringa, Integer maxSize, String name) throws RemoteException, NotBoundException {
		
		String command = "SUBSCRIBEREQUEST "+stringa +" "+maxSize+" "+name;
		server.send(command);
		
		System.out.println(server.receive());
		return Integer.parseInt(server.receive());
	}

	
	
	
	
	
	
	
	@Override
	public PlayerDTO moveRequest(String sector, Integer yourId, Integer gameNumber) throws RemoteException {

		return null;
	}

	@Override
	public PlayerDTO attackRequest(Integer yourId, Integer gameNumber) throws RemoteException {

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