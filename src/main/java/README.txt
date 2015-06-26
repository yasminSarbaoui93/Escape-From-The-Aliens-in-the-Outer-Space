To start the game:
1)Run the class MainServer.java in the package connection.
2)For each client you want to start run an instance of the class MainViewGui.java in the package view.

Note that:
1) the game support Rmi and socket connection but you cannot start a game with players that use different type of connection.
2) for Moving or Bluffing(when you draw the card Noise in any sector you must declare a sector where you want to make noise)
you must click the right mouse button clicking on the sector you want to move or bluff.
3) you can discard a card only if you have 4 cards in your hand.
4) on the Log Panel the green messages are answers from the server, the white messages are the publish from the broker!
5) when the game starts you must insert your username, this username don't uniquely identify a player in the game, the 
PlayerId that you receive from the server will do this.