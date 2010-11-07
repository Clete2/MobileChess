package shared;


public class Game {
	Board theBoard;
	
	public Game() {
		setupGame();
	}
	
	private void setupGame() {
		theBoard = new Board();
	}
	
	public Board getBoard() {
		return theBoard;
	}
}
