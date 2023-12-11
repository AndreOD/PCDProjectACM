package main.game;

import main.environment.Cell;
import main.environment.LocalBoard;


public class ObstacleMover implements Runnable {
	private Obstacle obstacle;
	private LocalBoard board;
	private static final int OBSTACLE_MOVE_INTERVAL = 400; //Milliseconds between moves


	// Constructors
	public ObstacleMover(Obstacle obstacle, LocalBoard board) {
		super();
		this.obstacle = obstacle;
		this.board = board;
	}

	// Thread Parent Class
	@Override
	public void run() {
		try {
			while (obstacle.getRemainingMoves() > 0) {
				removeObstacle();
				obstacle.decrementRemainingMoves();
				addObstacle();

				Thread.sleep(OBSTACLE_MOVE_INTERVAL);
			}
		} catch (InterruptedException e) {}

	}

	private void addObstacle(){
		board.addGameElement(obstacle);
		board.setChanged();
	}

	private void removeObstacle() {
		for (Cell[] list : board.getCells()) {
			for (Cell c : list) {
				if (c.getObstacle() == obstacle) {
					c.removeObstacle();
					return;
				}}}
	}

}
