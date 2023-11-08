package main.game;

import main.environment.LocalBoard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObstacleMover implements Runnable {
	private Obstacle obstacle;
	private LocalBoard board;
	private static final int MOVING_OBSTACLES_NUMBER = 3;

	// Constructors
	public ObstacleMover(Obstacle obstacle, LocalBoard board) {
		super();
		this.obstacle = obstacle;
		this.board = board;
	}

	public ObstacleMover(LocalBoard board) {
		super();
		this.board = board;
	}
	// Thread Class
	@Override
	public void run() {
		obstacle.move();
	}
}
