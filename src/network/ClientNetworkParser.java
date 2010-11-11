package network;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shared.Game;
import shared.PieceColor;

public class ClientNetworkParser extends NetworkParser {	
	private static final Exception NoMatchException = null;
	Game theGame;

	public ClientNetworkParser(Game theGame) {
		this.theGame = theGame;
	}

	public void parseInput(String in) throws Exception {
		// User asked for color
		if(in.equalsIgnoreCase("W")) {
			theGame.setLocalColor(PieceColor.WHITE);
			return;
		} else if(in.equalsIgnoreCase("B")) {
			theGame.setLocalColor(PieceColor.BLACK);
			return;
		}
		// User must have sent coordinates
		Pattern coordPattern = Pattern.compile("(\\d{1,2})\\,(\\d{1,2})");
		Matcher coordMatcher = coordPattern.matcher(in);
		if(!coordMatcher.matches()) {
			throw NoMatchException;
		}
		try {
			theGame.getBoard().getPieceList()
			.movePiece((Short.parseShort(coordMatcher.group(2))), 
					theGame.getBoard().getPieceList()
					.getPieceAtIndex(Integer.parseInt(coordMatcher.group(1))),
					theGame);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
