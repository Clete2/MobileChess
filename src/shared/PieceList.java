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

		for(Short i = 0; i < 64; i++) {
				pieces[i] = new Piece(PieceColor.NONE, PieceType.EMPTY);
		}
		moveParse = new MoveParser();
	}

	public void addPiece(short index, PieceColor pieceColor, PieceType pieceType) {
		pieces[index].setColor(pieceColor);
		pieces[index].setPieceType(pieceType);
	}

	public void movePiece(short newIndex, Piece pieceToMove, Game theGame) throws Exception {
		if(!moveParse.isMoveValid(newIndex, pieceToMove)) {
			throw InvalidMoveException;
		}

		updatePiece((short)getPieceLocation(pieceToMove), newIndex, pieceToMove);
		pieces[getPieceLocation(pieceToMove)] = new Piece(PieceColor.NONE, PieceType.EMPTY);
		pieces[newIndex] = pieceToMove;
		theGame.incrementGameCounter();
		pieceSelected = null;
	}

	public void updatePiece(short oldIndex,
			short newIndex, Piece pieceToUpdate) {
		ClientWindow.getChessLabels().elementAt(oldIndex).setIcon(null);
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

		try {
			movePiece((short)getPieceLocation(pieceToWatch), pieceSelected, theGame);
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
	public int getPieceLocation(Piece piece) {
		for(int i = 0; i < 64; i++) {
			if(pieces[i] == piece) {
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
