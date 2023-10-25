package main.environment;

import java.io.Serializable;

import javax.sound.midi.SysexMessage;

import main.game.GameElement;
import main.game.Goal;
import main.game.Obstacle;
import main.game.Snake;
import main.game.AutomaticSnake;

/**
 * Main class for game representation.
 * 
 * @author luismota
 *
 */
public class Cell {
	private BoardPosition position;
	private Snake ocuppyingSnake = null;
	private GameElement gameElement = null;

	// Constructors
	public Cell(BoardPosition position) {
		super();
		this.position = position;
	}

	// Getters
	public BoardPosition getPosition() {
		return position;
	}

	public Snake getOcuppyingSnake() {
		return ocuppyingSnake;
	}

	public GameElement getGameElement() {
		return gameElement;
	}

	public Goal getGoal() {
		return (Goal) gameElement;
	}

	// Setters
	public void setGameElement(GameElement element) {
		// TODO coordination and mutual exclusion
		gameElement = element;
	}
	
	public void request(Snake snake)
			throws InterruptedException {
		// TODO coordination and mutual exclusion
		ocuppyingSnake = snake;
	}

	public void release() {
		// TODO
	}

	// Removes
	public Goal removeGoal() {
		// TODO
		return null;
	}

	public void removeObstacle() {
		// TODO
	}

	// Booleans
	public boolean isOcupiedByGoal() {
		return (gameElement != null && gameElement instanceof Goal);
	}

	public boolean isOcupiedBySnake() {
		return ocuppyingSnake != null;
	}

	/**
	 * This function basically verifies if this Cell is transposable.
	 * 
	 * @return {@code true} if is a Snake or an Obstacle.
	 */
	public boolean isOcupiedBySnakeOrObstacle() {
		return isOcupiedBySnake() || (gameElement != null && gameElement instanceof Obstacle);
	}

}
