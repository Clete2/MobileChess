package shared;

public class Player {
	private PieceColor playerColor;
	
	public Player(PieceColor playerColor) {
		this.playerColor = playerColor;
	}
	
	public PieceColor getPlayerColor() {
		return playerColor;
	}
}
