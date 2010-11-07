package shared;


public class Game {
	Board theBoard;
	
	public Game() {
		setupGame();
	}
	
	private void setupGame() {
		theBoard = new Board();
		Player whitePlayer = new Player(PieceColor.WHITE);
		Player blackPlayer = new Player(PieceColor.BLACK);
	}
	
	public Board getBoard() {
		return theBoard;
	}
}
