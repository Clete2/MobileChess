package shared;

import java.net.URL;

import javax.swing.ImageIcon;

public class PieceList {
	private static final Exception PieceNotOnBoardException = null;
	private static final Exception InvalidMoveException = null;
	private Piece[][] pieces;
	private MoveParser moveParse;

	public PieceList() {
		pieces = new Piece[8][8];

		for(Short i = 0; i < 8; i++) {
			for(Short j = 0; j < 8; j++) {
				pieces[i][j] = new Piece(PieceColor.NONE, PieceType.EMPTY);
			}
		}

		moveParse = new MoveParser();
	}

	public void addPiece(Short startX, Short startY, PieceColor pieceColor, PieceType pieceType) {
		assert(pieces[startX][startY].getPieceColor() != PieceColor.NONE);
		assert(startX < 7);
		assert(startY < 7);

		pieces[startX][startY].setColor(pieceColor);
		pieces[startX][startY].setPieceType(pieceType);
	}

	public Piece getPieceAt(Short x, Short y) {
		return pieces[x][y];
	}

	public void movePiece(short newX, short newY, Piece pieceToMove, Player playerMoving) throws Exception {
		boolean found = false;
		short x = -1;
		short y = -1;

		for(Short i = 0; i < 8; i++) {
			for(Short j = 0; j < 8; j++) {
				if(pieces[i][j].equals(pieceToMove)) {
					found = true;
					x = i;
					y = j;
					break;
				}
			}
			if(found == true) {
				break;
			}
		}

		if(!found) {
			throw PieceNotOnBoardException;
		}
		if(!moveParse.isMoveValid(newX, newY, pieceToMove, playerMoving)) {
			throw InvalidMoveException;
		}

		pieces[x][y] = new Piece(PieceColor.NONE, PieceType.EMPTY);
		pieces[newX][newY] = pieceToMove;
	}

	public ImageIcon getImageForPiece(Piece piece) {
		if(piece.getPieceType().toString().equalsIgnoreCase("EMPTY") ||
				piece.getPieceColor().toString().equalsIgnoreCase("NONE")) {
			return null;
		}
		
		URL pieceURL = getClass().getResource("/images/" + 
				piece.getPieceColor().toString() + 
				piece.getPieceType().toString() + ".png");
		return new ImageIcon(pieceURL);
	}
}
