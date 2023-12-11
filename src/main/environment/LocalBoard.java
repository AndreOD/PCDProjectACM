package main.environment;

import main.concurrent.ThreadPool;
import main.game.*;

/** Class representing the state of a game running locally
 * 
 * @author luismota
 *
 */
public class LocalBoard extends Board{
	
	private static final int NUM_SNAKES = 2;
	private static final int NUM_OBSTACLES = 25;
	private static final int NUM_SIMULTANEOUS_MOVING_OBSTACLES = 3;

	private transient ThreadPool threadPool;

	// Construtors
	public LocalBoard() {

		for (int i = 0; i < NUM_SNAKES; i++) {
			AutomaticSnake snake = new AutomaticSnake(i, this);
			snakes.add(snake);
		}

		addObstacles(NUM_OBSTACLES);
		addGoal();
	}

	// Init
	public void init() {
		for(Snake s:snakes)
			s.start();
		setChanged();
		threadPool = new ThreadPool(NUM_SIMULTANEOUS_MOVING_OBSTACLES); // ThreadPool for ObstacleMovers
		getObstacles().forEach(obstacle -> threadPool.submit(new ObstacleMover(obstacle,this)));

	}

	public void endThreadPool(){
		threadPool.interruptAll();
	}

	// Board Class
	@Override
	public void handleKeyPress(int keyCode) {
		// do nothing... No keys relevant in local game
	}

	@Override
	public void handleKeyRelease() {
		// do nothing... No keys relevant in local game
	}

}
