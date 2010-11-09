package shared;

public abstract class Player {
	private PieceColor playerColor;
	
	public Player(PieceColor playerColor) {
		this.playerColor = playerColor;
	}
	
	public PieceColor getPlayerColor() {
		return playerColor;
	}
}
