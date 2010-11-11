package shared;

public class Board {
	private PieceList pieces;
	
	public Board(){
		pieces = new PieceList();
		
		initializeWhitePlayer();
		initializeBlackPlayer();
		initializeEmptySpaces();
	}
	
	private void initializeWhitePlayer() {
		pieces.addPiece((short)56, PieceColor.WHITE, PieceType.ROOK);
		pieces.addPiece((short)57, PieceColor.WHITE, PieceType.KNIGHT);
		pieces.addPiece((short)58, PieceColor.WHITE, PieceType.BISHOP);
		pieces.addPiece((short)59, PieceColor.WHITE, PieceType.QUEEN);
		pieces.addPiece((short)60, PieceColor.WHITE, PieceType.KING);
		pieces.addPiece((short)61, PieceColor.WHITE, PieceType.BISHOP);
		pieces.addPiece((short)62, PieceColor.WHITE, PieceType.KNIGHT);
		pieces.addPiece((short)63, PieceColor.WHITE, PieceType.ROOK);
		
		for(short i = 0; i < 8; i++) {
			pieces.addPiece((short)(i + 48), PieceColor.WHITE, PieceType.PAWN);
		}
	}
	
	private void initializeBlackPlayer() {
		pieces.addPiece((short)0, PieceColor.BLACK, PieceType.ROOK);
		pieces.addPiece((short)1, PieceColor.BLACK, PieceType.KNIGHT);
		pieces.addPiece((short)2, PieceColor.BLACK, PieceType.BISHOP);
		pieces.addPiece((short)3, PieceColor.BLACK, PieceType.QUEEN);
		pieces.addPiece((short)4, PieceColor.BLACK, PieceType.KING);
		pieces.addPiece((short)5, PieceColor.BLACK, PieceType.BISHOP);
		pieces.addPiece((short)6, PieceColor.BLACK, PieceType.KNIGHT);
		pieces.addPiece((short)7, PieceColor.BLACK, PieceType.ROOK);
		
		for(short i = 0; i < 8; i++) {
			pieces.addPiece((short)(i + 8), PieceColor.BLACK, PieceType.PAWN);
		}
	}

	private void initializeEmptySpaces() {
		for(int i = 0; i < 32; i++) {
			pieces.addPiece((short)(i + 16), PieceColor.NONE, PieceType.EMPTY);
		}
	}
	
	public PieceList getPieceList() {
		return pieces;
	}
}
