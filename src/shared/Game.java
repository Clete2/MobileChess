package shared;


public class Game {
	private Board theBoard;
	private Player whitePlayer;
	private Player blackPlayer;
	private int gameCounter;
	
	public Game() {
		setupLocalGame();
	}
	
	public Game(byte[] ip, short port) {
		setupNetworkGame(ip, port);
	}
	
	private void setupLocalGame() {
		whitePlayer = new LocalPlayer(PieceColor.WHITE);
		blackPlayer = new LocalPlayer(PieceColor.BLACK);
		theBoard = new Board();
		gameCounter = 0;
	}
	
	private void setupNetworkGame(byte[] ip, short port) {
		whitePlayer = new NetworkPlayer(PieceColor.WHITE);
		blackPlayer = new NetworkPlayer(PieceColor.BLACK);
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
	
	public Player getWhitePlayer() {
		return whitePlayer;
	}
	
	public Player getBlackPlayer() {
		return blackPlayer;
	}
}
