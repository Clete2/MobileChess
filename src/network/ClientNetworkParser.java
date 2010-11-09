package network;

import shared.Game;
import shared.PieceColor;

public class ClientNetworkParser extends NetworkParser {	
	Game theGame;
	
	public ClientNetworkParser(Game theGame) {
		this.theGame = theGame;
	}

	public void parseInput(String in) {
		if(in.equalsIgnoreCase("W") || in.equalsIgnoreCase("B")) { // User asked for color
			theGame.notify();
			if(in.equalsIgnoreCase("W")) {
				theGame.setLocalColor(PieceColor.WHITE);
			} else {
				theGame.setLocalColor(PieceColor.BLACK);
			}
		}
	}
}
