package main.environment;

/**
 * Classe representing a position on the board, with some utilities
 * 
 * @author luismota
 *
 */

public class BoardPosition {
	public final int x;
	public final int y;

	// Constructors
	public BoardPosition(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	// Getters
	public BoardPosition getCellAbove() {
		return new BoardPosition(x, y - 1);
	}

	public BoardPosition getCellBelow() {
		return new BoardPosition(x, y + 1);
	}

	public BoardPosition getCellLeft() {
		return new BoardPosition(x - 1, y);
	}

	public BoardPosition getCellRight() {
		return new BoardPosition(x + 1, y);
	}

	// Auxiliar Methods
	public double distanceTo(BoardPosition other) {
		double delta_x = y - other.y;
		double delta_y = x - other.x;
		return Math.sqrt(delta_x * delta_x + delta_y * delta_y);
	}

	/**
	 * Calculates normalized vector to specific BoardPosition.
	 * @param goalPostion - BoardPosition that is wanted to achive.
	 * @return {@code BoardPosition Normalized} - Possible values [0, 1], [0, -1], [1, 0] or [-1, 0].
	 */
	public BoardPosition vectorTo(BoardPosition goalPostion) {
		int dx = goalPostion.x - x;
		int dy = goalPostion.y - y;
		
		if (Math.abs(dx) > Math.abs(dy))
			return new BoardPosition(Integer.signum(dx), 0);

		return new BoardPosition(0, Integer.signum(dy));
	}

	/**
	 * Adds two BoardPositions.
	 * @param other - BoardPosition to add.
	 * @return {@code BoardPosition} - result of the addition.
	 */
	public BoardPosition plus(BoardPosition other){
		return new BoardPosition(x + other.x, y + other.y);
	}

	// Object Class
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public boolean equals(Object obj) {
		BoardPosition other = (BoardPosition) obj;
		return other.x == x && other.y == y;
	}
}
