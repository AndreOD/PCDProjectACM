package main.game;

import main.environment.LocalBoard;

public class ObstacleMover extends Thread {
	private Obstacle obstacle;
	private LocalBoard board;

	// Constructors
	public ObstacleMover(Obstacle obstacle, LocalBoard board) {
		super();
		this.obstacle = obstacle;
		this.board = board;
	}

	// Thread Class
	@Override
	public void run() {
		// obstacle.move();
	}
}
