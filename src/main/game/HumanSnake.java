package main.game;

import java.io.BufferedReader;
import java.io.IOException;

import main.concurrent.BarrierTimeout;
import main.environment.Board;
import main.environment.BoardPosition;
import main.environment.Direction;

/**
 * Class for a remote snake, controlled by a human
 * 
 * @author luismota
 *
 */
public class HumanSnake extends Snake {

	private transient Direction direction = Direction.NULL;
	private transient BufferedReader in;

	// Constructors
	public HumanSnake(int id, Board board, BufferedReader in) {
		super(id, board);
		this.in = in;
	}

	@Override
	public void run() {
		doInitialPositioning();
		System.err.println(super.toString() + " initial size:" + cells.size());

		// Wait for MILLISECONDS_TO_JOIN_BEFORE_GAME
		BarrierTimeout.getInstance().waitForTimeout();

		while (!getBoard().isFinished()) {
			try {
				if (in.ready())
					setDirection(in.readLine());

				BoardPosition nextMove = nextMove();

				if (nextMove.x < 0 || nextMove.x >= Board.NUM_COLUMNS || nextMove.y < 0
						|| nextMove.y >= Board.NUM_ROWS) {
					direction = Direction.NULL;
					continue; // ignore movement
				}
				move(getBoard().getCell(nextMove()));
			} catch (InterruptedException | IOException e) {
			}
		}
	}

	public void setDirection(String directionString) {
		switch (directionString) {
			case "U":
				direction = Direction.UP;
				break;
			case "D":
				direction = Direction.DOWN;
				break;
			case "L":
				direction = Direction.LEFT;
				break;
			case "R":
				direction = Direction.RIGHT;
		}
	}

	private BoardPosition nextMove() {
		return getHeadPosition().plus(direction.getVector());
	}

}
