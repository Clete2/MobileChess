package shared;

public class Player {
	private PieceColor playerColor;
	
	public Player(PieceColor playerColor) {
		this.playerColor = playerColor;
	}
	
	public void movePiece(short newX, short newY, Piece pieceToMove, Board theBoard) {
		try {
			theBoard.getPieceList().movePiece(newX, newY, pieceToMove, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PieceColor getPlayerColor() {
		return playerColor;
	}
}
