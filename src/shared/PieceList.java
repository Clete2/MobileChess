package shared;

import java.net.URL;

import javax.swing.ImageIcon;
import client.ClientWindow;

public class PieceList {
	private static final Exception PieceNotOnBoardException = null;
	private static final Exception InvalidMoveException = null;
	private Piece[] pieces;
	private MoveParser moveParse;
	private Piece pieceSelected;

	public PieceList() {
		pieceSelected = null;
		pieces = new Piece[64];

		for(Short i = 0; i < 8; i++) {
			for(Short j = 0; j < 8; j++) {
				pieces[i + (8 * j)] = new Piece(PieceColor.NONE, PieceType.EMPTY);
			}
		}
		moveParse = new MoveParser();
	}

	public void addPiece(Short startRow, Short startCol, PieceColor pieceColor, PieceType pieceType) {
		assert(pieces[startRow + (8 * startCol)].getPieceColor() != PieceColor.NONE);
		assert(startRow < 7);
		assert(startCol < 7);

		pieces[startRow + (8 * startCol)].setColor(pieceColor);
		pieces[startRow + (8 * startCol)].setPieceType(pieceType);
	}

	public Piece getPieceAt(Short row, Short col) {
		return pieces[row + (8 * col)];
	}

	public void movePiece(short newRow, short newCol, Piece pieceToMove, Player playerMoving) throws Exception {
		// TODO Make sure that it is that player's turn.

		short[] oldLocation = getPieceLocation(pieceToMove);

		if(!moveParse.isMoveValid(newRow, newCol, pieceToMove, playerMoving)) {
			throw InvalidMoveException;
		}

		updatePiece(oldLocation[0], oldLocation[1], newRow, newCol, pieceToMove);
		pieces[oldLocation[0] + (8 * oldLocation[1])] = new Piece(PieceColor.NONE, PieceType.EMPTY);
		pieces[newRow + (8 * newCol)] = pieceToMove;
	}

	private short[] getPieceLocation(Piece pieceToSearchFor) throws Exception {
		boolean found = false;
		short row = -1;
		short col = -1;

		
		for(Short i = 0; i < 8; i++) {
			for(Short j = 0; j < 8; j++) {
				if(pieces[i + (8 * j)].equals(pieceToSearchFor)) {
					found = true;
					row = i;
					col = j;
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
		
		return new short[] {row, col};
	}

	public void updatePiece(short oldRow, short oldCol, short newRow, short newCol, Piece pieceToUpdate) {
		ClientWindow.getChessLabels().elementAt((oldRow * 8) + oldCol).setIcon(null);
		ClientWindow.getChessLabels().elementAt((newRow * 8) + newCol).setIcon(getImageForPiece(pieceToUpdate));
	}
	
	public ImageIcon getImageForPiece(Piece piece) {	
		URL pieceURL = getClass().getResource("/images/" + 
				piece.getPieceColor().toString() + 
				piece.getPieceType().toString() + ".png");
		return new ImageIcon(pieceURL);
	}

	public void selectPieceOnLabel(Piece pieceToWatch) {
		if(pieceSelected == null) {
			pieceSelected = pieceToWatch;
			return;
		}
		short[] newLocation;
		try {
			newLocation = getPieceLocation(pieceToWatch);
			movePiece(newLocation[0], newLocation[1], pieceSelected, null); // TODO Change from new Player
		} catch (Exception e) {
			e.printStackTrace();
		}
		pieceSelected = null;
	}
}
