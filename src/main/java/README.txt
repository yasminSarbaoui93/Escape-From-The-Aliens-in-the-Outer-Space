To start the game:
1)Run the class MainServer.java in the package connection.
2)For each client you want to start run an instance of the class MainViewGui.java in the package view.

Note that:
1) the game supports Rmi and socket connection but not in the same match.
2) for Moving or Bluffing the position after drowing the card Noise in any sector,
you must click the right mouse button clicking on the sector where you want to move or bluff.
3) you can discard a card only if you have 4 cards in your hand.
4) on the Log Panel the green messages are answers from the server, the white messages are the publish from the broker!
5) when the game starts you must insert your username, this username don't uniquely identify a player in the game, the 
PlayerId that you receive from the server will do this.