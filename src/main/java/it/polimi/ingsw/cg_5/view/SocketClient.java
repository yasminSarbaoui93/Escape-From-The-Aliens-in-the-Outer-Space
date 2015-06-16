package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SocketClient implements Client {
	
	private String ip;
	private int port;

	
	public SocketClient(String ip, int port){
		this.ip=ip;
		this.port=port;
	}
	
	public void startClient(){
		try{
			String command = "";
			Scanner stdin = new Scanner(System.in);
			Socket socket = new Socket(ip, port);
			SocketCommunicator server = new SocketCommunicator(socket);
			
			do{			
				command = stdin.nextLine();
				server.send(command);
				String response = server.receive();
				
			}
		}
	}

	@Override
	public Integer matchRequest(String stringa, Integer maxSize, String name) throws RemoteException, NotBoundException {
		
		return null;
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
