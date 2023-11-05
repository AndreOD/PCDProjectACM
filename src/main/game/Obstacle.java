package main.game;

import main.environment.Board;
import main.environment.LocalBoard;

public class Obstacle extends GameElement {
	private static final int NUM_MOVES = 3;
	private static final int OBSTACLE_MOVE_INTERVAL = 400;
	private int remainingMoves = NUM_MOVES;
	private Board board;

	// Constructors
	public Obstacle(Board board) {
		super();
		this.board = board;
	}

	// Getters
	public int getRemainingMoves() {
		return remainingMoves;
	}

	// Setters
	public void move() {
		// while (remainingMoves > 0) {
		// 	try {
		// 		board.addGameElement(this);

		// 		remainingMoves--;

		// 		Thread.sleep(OBSTACLE_MOVE_INTERVAL);
		// 	} catch (InterruptedException e) {
		// 		// TODO
		// 	}
		// }
	}
}
