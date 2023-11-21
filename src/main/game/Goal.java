package main.game;

import main.environment.Board;
import main.environment.LocalBoard;

public class Goal extends GameElement {
	public static final int MAX_VALUE = 9; // TODO Confirmar com o professor valor
	private int value = 1;
	private Board board;

	// Constructors
	public Goal(Board board2) {
		this.board = board2;
	}

	// Getters
	public int getValue() {
		return value;
	}

	// Setters
	/**
	 * If Goal did not reach the limit increments value of the Goal, otherwise finishes the game.
	 * 
	 * @throws InterruptedException used to cancel new Goal in the Board in captureGoal() method.
	 * 
	 * @see #captureGoal()
	 */
	public void incrementValue() {value++;}

	/**
	 * Increments value of Goal and change position of Goal.
	 * <p>
	 * Does not remove current Goal in Cell.
	 * 
	 * @return {@code value} of the Goal when is catched.
	 * 
	 * @see main.environment.Cell#removeGoal() removeGoal()
	 */
	public int captureGoal() {
		if (value < MAX_VALUE) {
			incrementValue();
			board.addGameElement(this);
		} else { // Game Ends
			board.setGoalPosition(null);
			board.getSnakes().forEach(t -> t.interrupt());
			if (board  instanceof LocalBoard)
				((LocalBoard)board).endThreadPool();
		}

		return value - 1;
	}
}
