package shared;

import client.ClientHandler;


public class Game {
	private Board theBoard;
	private Player whitePlayer;
	private Player blackPlayer;
	private ClientHandler clientHandler;
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
		clientHandler = new ClientHandler(ip, port, this);
		clientHandler.sendMessage("C"); // Ask for color
		// We will be using setLocalColor(PieceColor color) to continue initializing.
		// If it isn't called, there was a timeout.
		// TODO Re-implement this so it is proper.
	}
	
	public void setLocalColor(PieceColor color) { // Continue initializing
		if(color.equals(PieceColor.WHITE)) {
			whitePlayer = new LocalNetworkPlayer(PieceColor.WHITE);
			blackPlayer = new NetworkPlayer(PieceColor.BLACK);
		} else {
			whitePlayer = new NetworkPlayer(PieceColor.WHITE);
			blackPlayer = new LocalNetworkPlayer(PieceColor.BLACK);
		}
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
