package main.environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.game.GameElement;
import main.game.Goal;
import main.game.Obstacle;
import main.game.Server;
import main.game.Snake;
import main.game.AutomaticSnake;

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
//		System.err.println("All elements placed");
	}

	// Init
	public void init() {
		for(Snake s:snakes)
			s.start();
		// TODO: launch other threads
		setChanged();
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
