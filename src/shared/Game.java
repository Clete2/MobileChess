package shared;


public class Game {
	private Board theBoard;
	private int gameCounter;
	
	public Game() {
		setupGame();
	}
	
	private void setupGame() {
		theBoard = new Board();
		gameCounter = 0;
	}
	
	public Board getBoard() {
		return theBoard;
	}
	
	public void incrementGameCounter() {
		gameCounter++;
	}
	
	public int getGameCounter() {
		return gameCounter;
	}
}
