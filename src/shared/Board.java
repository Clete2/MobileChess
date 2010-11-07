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
		pieces.addPiece((short)7, (short)0, PieceColor.WHITE, PieceType.ROOK);
		pieces.addPiece((short)7, (short)1, PieceColor.WHITE, PieceType.KNIGHT);
		pieces.addPiece((short)7, (short)2, PieceColor.WHITE, PieceType.BISHOP);
		pieces.addPiece((short)7, (short)3, PieceColor.WHITE, PieceType.QUEEN);
		pieces.addPiece((short)7, (short)4, PieceColor.WHITE, PieceType.KING);
		pieces.addPiece((short)7, (short)5, PieceColor.WHITE, PieceType.BISHOP);
		pieces.addPiece((short)7, (short)6, PieceColor.WHITE, PieceType.KNIGHT);
		pieces.addPiece((short)7, (short)7, PieceColor.WHITE, PieceType.ROOK);
		
		for(int i = 0; i < 8; i++) {
			pieces.addPiece((short)6, (short)i, PieceColor.WHITE, PieceType.PAWN);
		}
	}
	
	private void initializeBlackPlayer() {
		pieces.addPiece((short)0, (short)0, PieceColor.BLACK, PieceType.ROOK);
		pieces.addPiece((short)0, (short)1, PieceColor.BLACK, PieceType.KNIGHT);
		pieces.addPiece((short)0, (short)2, PieceColor.BLACK, PieceType.BISHOP);
		pieces.addPiece((short)0, (short)3, PieceColor.BLACK, PieceType.QUEEN);
		pieces.addPiece((short)0, (short)4, PieceColor.BLACK, PieceType.KING);
		pieces.addPiece((short)0, (short)5, PieceColor.BLACK, PieceType.BISHOP);
		pieces.addPiece((short)0, (short)6, PieceColor.BLACK, PieceType.KNIGHT);
		pieces.addPiece((short)0, (short)7, PieceColor.BLACK, PieceType.ROOK);
		
		for(int i = 0; i < 8; i++) {
			pieces.addPiece((short)1, (short)i, PieceColor.BLACK, PieceType.PAWN);
		}
	}

	private void initializeEmptySpaces() {
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				pieces.addPiece((short)i, (short)j, PieceColor.NONE, PieceType.EMPTY);
			}
		}
	}
	
	public void printColorMask() {
		for(short i = 0; i < 8; i++) {
			for(short j = 0; j < 8; j++) {
				System.out.printf("%8s", pieces.getPieceAt(i, j).getPieceColor());
			}
			System.out.println();
		}
	}
	
	public void printBoardSquares() {
		for(short i = 0; i < 8; i++) {
			for(short j = 0; j < 8; j++) {
				System.out.printf("%8s", pieces.getPieceAt(i, j).getPieceType());
			}
			System.out.println();
		}
	}
	
	public PieceList getPieceList() {
		return pieces;
	}
}
