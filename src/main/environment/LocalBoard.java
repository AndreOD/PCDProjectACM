package main.environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

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

	// Construtors
	public LocalBoard() {

		for (int i = 0; i < NUM_SNAKES; i++) {
			AutomaticSnake snake = new AutomaticSnake(i, this);
			snakes.add(snake);
		}

		addObstacles(NUM_OBSTACLES);
		Goal goal=addGoal();
	}

	// Init
	public void init() {
		for(Snake s:snakes)
			s.start();

		setChanged();

		// ThreadingPoolWorking for ObstacleMovers
		ThreadPool threadPool = new ThreadPool(NUM_SIMULTANEOUS_MOVING_OBSTACLES);
		getObstacles().forEach(obstacle -> threadPool.submit(new ObstacleMover(obstacle,this)));


		// TODO: launch other threads

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
