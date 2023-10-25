package main.game;

import main.environment.Board;
import main.environment.LocalBoard;

public class Goal extends GameElement {
	public static final int MAX_VALUE = 10;
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
	public void incrementValue() throws InterruptedException {
		// TODO
	}

	public int captureGoal() {
		// TODO
		return -1;
	}
}
