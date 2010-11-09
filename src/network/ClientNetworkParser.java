package network;

import shared.Game;
import shared.PieceColor;

public class ClientNetworkParser {
	Game theGame;
	
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
