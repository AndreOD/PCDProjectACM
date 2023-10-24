package main.environment;

import java.io.Serializable;

import javax.sound.midi.SysexMessage;

import main.game.GameElement;
import main.game.Goal;
import main.game.Obstacle;
import main.game.Snake;
import main.game.AutomaticSnake;
/** Main class for game representation. 
 * 
 * @author luismota
 *
 */
public class Cell {
	private BoardPosition position;
	private Snake ocuppyingSnake = null;
	private GameElement gameElement=null;
	
	public GameElement getGameElement() {
		return gameElement;
	}


	public Cell(BoardPosition position) {
		super();
		this.position = position;
	}

	public BoardPosition getPosition() {
		return position;
	}

	public void request(Snake snake)
			throws InterruptedException {
		//TODO coordination and mutual exclusion
		ocuppyingSnake=snake;
	}

	public void release() {
		//TODO
	}

	public boolean isOcupiedBySnake() {
		return ocuppyingSnake!=null;
	}


	public  void setGameElement(GameElement element) {
		// TODO coordination and mutual exclusion
		gameElement=element;

	}

	public boolean isOcupied() {
		return isOcupiedBySnake() || (gameElement!=null && gameElement instanceof Obstacle);
	}


	public Snake getOcuppyingSnake() {
		return ocuppyingSnake;
	}


	public  Goal removeGoal() {
		// TODO
		return null;
	}
	public void removeObstacle() {
	//TODO
	}


	public Goal getGoal() {
		return (Goal)gameElement;
	}


	public boolean isOcupiedByGoal() {
		return (gameElement!=null && gameElement instanceof Goal);
	}
	
	

}
