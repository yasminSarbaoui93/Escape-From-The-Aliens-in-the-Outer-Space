package it.polimi.ingsw.cg_5.controller.connections;

public class ClientHandler extends Thread {
	
	Communicator client;
	public ClientHandler(Communicator c) {
		client = c;
	}
	
	@Override
	public void run(){
		String command = "";

		do {
			//gets an input command from the client socket to a string
            command = client.receive();
            
            //sends the string message to the server
            client.send(command);
        } while(!command.equals("exit"));
        client.close();
    }

}