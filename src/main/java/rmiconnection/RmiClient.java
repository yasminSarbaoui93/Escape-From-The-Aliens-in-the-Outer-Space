package rmiconnection;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RmiClient {
	private static final String NAME = "room";
	private final static String HOST="127.0.0.1"; 
	private final static int PORT=1099;
	RemoteMethods remoteMethods1;

	
	public RmiClient () throws Exception{		
		try{
			Registry registry = LocateRegistry.getRegistry(HOST,PORT);
			remoteMethods1 = (RemoteMethods) registry.lookup(NAME); 
			
		}
		catch( Exception e){
		e.printStackTrace();
		//System.err.println("Connessione Fallita!");
		}
	}
	public Integer matchRequest(String stringa, Integer maxSize) throws RemoteException{
		return remoteMethods1.SubscribeRequest(stringa, maxSize);
	}
	public String moveRequest(String sector, Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performMove(sector, yourId,gameNumber);
	}
	public String attackRequest(Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performAttack(yourId,gameNumber);
	}
	public String endTurnRequest(Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performEndTurn(yourId,gameNumber);
	}
	public String drawCardRequest(Integer yourId, Integer gameNumber) throws RemoteException {
		return remoteMethods1.performDrawCard(yourId, gameNumber);
	}

}