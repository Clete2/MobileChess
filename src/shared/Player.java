package shared;

public class Player {
	private PieceColor playerColor;
	
	public Player(PieceColor playerColor) {
		this.playerColor = playerColor;
	}
	
	public void movePiece(short newRow, short newCol, Piece pieceToMove, Board theBoard) {
		try {
			theBoard.getPieceList().movePiece(newRow, newCol, pieceToMove, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PieceColor getPlayerColor() {
		return playerColor;
	}
}
