package shared;

import java.net.URL;

import javax.swing.ImageIcon;
import client.ClientWindow;

public class PieceList {
	private static final Exception PieceNotOnBoardException = null;
	private static final Exception InvalidMoveException = null;
	private Piece[][] pieces;
	private MoveParser moveParse;
	private Piece pieceSelected;

	public PieceList() {
		pieceSelected = null;
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
		// TODO Make sure that it is that player's turn.

		short[] oldLocation = getPieceLocation(pieceToMove);

		if(!moveParse.isMoveValid(newX, newY, pieceToMove, playerMoving)) {
			throw InvalidMoveException;
		}

		updatePiece(oldLocation[0], oldLocation[1], newX, newY, pieceToMove);
		pieces[oldLocation[0]][oldLocation[1]] = new Piece(PieceColor.NONE, PieceType.EMPTY);
		pieces[newX][newY] = pieceToMove;
	}

	private short[] getPieceLocation(Piece pieceToSearchFor) throws Exception {
		boolean found = false;
		short x = -1;
		short y = -1;

		
		for(Short i = 0; i < 8; i++) {
			for(Short j = 0; j < 8; j++) {
				if(pieces[i][j].equals(pieceToSearchFor)) {
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
		
		return new short[] {x, y};
	}

	public void updatePiece(short oldX, short oldY, short newX, short newY, Piece pieceToUpdate) {
		ClientWindow.getChessLabels().elementAt((oldX * 8) + oldY).setIcon(null);
		ClientWindow.getChessLabels().elementAt((newX * 8) + newY).setIcon(getImageForPiece(pieceToUpdate));
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
