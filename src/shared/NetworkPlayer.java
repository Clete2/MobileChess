package shared;

public class NetworkPlayer extends Player {
	public NetworkPlayer(PieceColor playerColor) {
		super(playerColor);
	}

	public void selectPiece(Piece piece, Game theGame) {
		if(theGame.getBoard().getPieceList().isPieceSelected()) {
			String messageToSend = "";
			messageToSend +=
				theGame.getBoard().getPieceList().getPieceLocation(
						theGame.getBoard().getPieceList().getPieceSelected());
			messageToSend += ",";
			messageToSend += 
				theGame.getBoard().getPieceList().getPieceLocation(piece);
			theGame.getClientHandler().sendMessage(messageToSend);
			try {
				theGame.getBoard().getPieceList().movePiece((short)theGame.getBoard().getPieceList().getPieceLocation(piece),
						theGame.getBoard().getPieceList().getPieceSelected(), theGame);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			theGame.getBoard().getPieceList().selectPiece(piece, theGame);
		}
	}
}
