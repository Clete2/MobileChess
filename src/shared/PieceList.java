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
	private Player pieceSelector;

	public PieceList() {
		pieceSelected = null;
		pieceSelector = null;
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

	public void movePiece(short newRow, short newCol, Piece pieceToMove, Game theGame) throws Exception {
		if(!moveParse.isMoveValid(newRow, newCol, pieceToMove)) {
			throw InvalidMoveException;
		}

		short[] oldLocation = getPieceLocation(pieceToMove);
		updatePiece(oldLocation[0], oldLocation[1], newRow, newCol, pieceToMove);
		pieces[oldLocation[0] + (8 * oldLocation[1])] = new Piece(PieceColor.NONE, PieceType.EMPTY);
		pieces[newRow + (8 * newCol)] = pieceToMove;
		theGame.incrementGameCounter();
		pieceSelected = null;
	}

	public void movePiece(short newIndex, Piece pieceToMove, Game theGame) throws Exception {
		if(!moveParse.isMoveValid(newIndex, pieceToMove)) {
			throw InvalidMoveException;
		}

		short[] oldLocation = getPieceLocation(pieceToMove);
		updatePiece(oldLocation[0], oldLocation[1], newIndex, pieceToMove);
		pieces[oldLocation[0] + (8 * oldLocation[1])] = new Piece(PieceColor.NONE, PieceType.EMPTY);
		pieces[newIndex] = pieceToMove;
		theGame.incrementGameCounter();
		pieceSelected = null;
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

	public void updatePiece(short oldRow, short oldCol, short newRow,
			short newCol, Piece pieceToUpdate) {
		ClientWindow.getChessLabels().elementAt((oldRow * 8) + 
				oldCol).setIcon(null);
		ClientWindow.getChessLabels().elementAt((newRow * 8) + 
				newCol).setIcon(getImageForPiece(pieceToUpdate));
	}

	public void updatePiece(short oldRow, short oldCol,
			short newIndex, Piece pieceToUpdate) {
		ClientWindow.getChessLabels().elementAt((oldRow * 8) + 
			oldCol).setIcon(null);
		ClientWindow.getChessLabels().elementAt(newIndex)
			.setIcon(getImageForPiece(pieceToUpdate));
	}

	public ImageIcon getImageForPiece(Piece piece) {	
		URL pieceURL = getClass().getResource("/images/" + 
				piece.getPieceColor().toString() + 
				piece.getPieceType().toString() + ".png");
		return new ImageIcon(pieceURL);
	}

	/**
	 * First selects a piece as a candidate for moving,
	 * then if the second selection is a valid move for that
	 * piece, it moves the first piece to the square of the second piece.
	 * @param pieceToWatch
	 * @param theGame
	 */
	public void selectPiece(Piece pieceToWatch, Game theGame) {
		if(pieceToWatch.getPieceColor().equals(PieceColor.NONE) &&
				pieceSelected == null) {
			return;
		}
		if(pieceSelected == null) {
			pieceSelected = pieceToWatch;
			pieceSelector = theGame.getPlayerForColor(pieceToWatch.getPieceColor());
			return;
		}
		if((theGame.getGameCounter() % 2 == 1 &&
				pieceSelected.getPieceColor().equals(PieceColor.WHITE)) ||
				(theGame.getGameCounter() % 2 == 0 &&
						pieceSelected.getPieceColor().equals(PieceColor.BLACK))) {
			pieceSelected = null;
			pieceSelector = null;
			return;
		}

		short[] newLocation;
		try {
			newLocation = getPieceLocation(pieceToWatch);
			movePiece(newLocation[0], newLocation[1], pieceSelected, theGame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pieceSelected = null;
	}

	public boolean isPieceSelected() {
		if(pieceSelected == null){
			return false;
		}
		return true;
	}

	public Piece getPieceSelected() {
		return pieceSelected;
	}

	public Piece getPieceAtIndex(int i ) {
		return pieces[i];
	}

	/**
	 * Gets the location of the piece given.
	 * @param piece Piece to look for.
	 * @return -1 if not found, an integer 0 through 63 otherwise.
	 */
	public int getLocationOfPiece(Piece piece) {
		for(int i = 0; i < 64; i++) {
			if(pieces[i].equals(piece)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the player that last selected a piece.
	 * @return
	 */
	public Player getPieceSelector() {
		return pieceSelector;
	}
}
