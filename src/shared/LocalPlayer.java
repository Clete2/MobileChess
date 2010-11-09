package shared;

public class LocalPlayer extends Player{
	public LocalPlayer(PieceColor playerColor) {
		super(playerColor);
	}
	
	public void selectPiece(Piece piece, Game theGame) {
		theGame.getBoard().getPieceList().selectPiece(piece, theGame);
	}
}
