package main.game;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.Position;

import main.environment.LocalBoard;
import main.gui.SnakeGui;
import main.environment.Cell;
import main.environment.Board;
import main.environment.BoardPosition;

public class AutomaticSnake extends Snake {

	// Constructors
	public AutomaticSnake(int id, LocalBoard board) {
		super(id, board);
	}

	// Move
	/**
	 * Calculates next position of snake's head.
	 * Does not verify if next position is occupied by any obstacles.
	 *
	 * @return {@code BoardPosition} - new Position closer to Goal
	 */
	BoardPosition nextMove(boolean random) {
		if (random){
			int randomDirectionIndex = ThreadLocalRandom.current().nextInt(4);
			return getBoard().getNeighboringPositions(getCells().getLast()).get(randomDirectionIndex);

		}else {
			BoardPosition headPostion = cells.getLast().getPosition();
			BoardPosition vectorToGoal = headPostion.vectorTo(getBoard().getGoalPosition());
			return headPostion.plus(vectorToGoal);
		}
	}


	boolean canMove() {
		return getBoard().getGoalPosition() != null;
	}

	// Thread Class
	@Override
	public void run() {
		doInitialPositioning();
		boolean isRandomMove = false;
		System.err.println(super.toString() + " initial size:" + cells.size());

			// cells.getLast().request(this); Prof did this
			while (canMove()) {
				try {
				// System.err.println(toString() + " trying to move to " + nextMove());
				move(getBoard().getCell(nextMove(isRandomMove)));
				isRandomMove = false;
				sleep(120);
				} catch (InterruptedException e) {
					isRandomMove = true;
				}
			}

	}

}
