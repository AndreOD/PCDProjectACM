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
	 * Verifies if next position equals the last position that blocked the Snake or
	 * if next position equals is a part of the Snake.
	 * Does not verify if next position is occupied by any Obstacles.
	 *
	 * @return {@code BoardPosition} - new Position closer to Goal
	 */
	BoardPosition nextMove(boolean random) {
		BoardPosition positionToGoal = getHeadPosition().plus(getHeadPosition().vectorTo(getBoard().getGoalPosition()));
		List<BoardPosition> neighboringPositions = getBoard().getNeighboringPositions(getHeadCell());
		List<BoardPosition> snakePositions = getPath(cell -> neighboringPositions.contains(cell.getPosition()));

		if (!random && !snakePositions.contains(positionToGoal))
			return positionToGoal;
			
		BoardPosition randPosition = neighboringPositions.get(ThreadLocalRandom.current().nextInt(neighboringPositions.size()));

		while (snakePositions.contains(randPosition) || randPosition.equals(positionToGoal))
			randPosition = neighboringPositions.get(ThreadLocalRandom.current().nextInt(neighboringPositions.size()));

		return randPosition;

	}

	boolean canMove() {
		//return getBoard().getGoalPosition() != null;
		return !getBoard().isFinished();
	}

	// Thread Class
	@Override
	public void run() {
		doInitialPositioning();
		boolean isRandomMove = false;
		System.err.println(super.toString() + " initial size:" + cells.size());

		while (canMove()) {
			try {
				// System.err.println(toString() + " trying to move to " + nextMove());
				move(getBoard().getCell(nextMove(isRandomMove)));
				isRandomMove = false;
			} catch (InterruptedException e) {
				isRandomMove = true;
			}
		}

	}

}
