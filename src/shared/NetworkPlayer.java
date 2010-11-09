package shared;

public class NetworkPlayer extends Player {
	public NetworkPlayer(PieceColor playerColor) {
		super(playerColor);
	}
	
	public void selectPiece(Piece piece, Game theGame) {
		String messageToSend = "";
		messageToSend +=
			theGame.getBoard().getPieceList().getLocationOfPiece(
					theGame.getBoard().getPieceList().getPieceSelected());
		messageToSend += ",";
		messageToSend += 
			theGame.getBoard().getPieceList().getLocationOfPiece(piece);
		System.out.println(messageToSend);
		theGame.getClientHandler().sendMessage(messageToSend);
	}
}
