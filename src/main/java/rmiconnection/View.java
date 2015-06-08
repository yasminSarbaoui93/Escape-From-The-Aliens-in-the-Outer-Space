package rmiconnection;



public class View  {
	int PlayerID=101;
	RmiClient rmiClient;
	
	
	public View () throws Exception{

	this.rmiClient= new RmiClient();
	
	}
	

	public int getPlayerID() {
		return PlayerID;
	}
	
	public static void main (String args []) throws Exception{
		new View();
	}


	

}
