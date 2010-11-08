package shared;

public class Piece {
	private PieceColor pieceColor;
	private PieceType pieceType;
	
	public Piece(PieceColor pieceColor, PieceType pieceType) {
		this.pieceColor = pieceColor;
		this.pieceType = pieceType;
	}
	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public void setColor(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}
}
