package main.game;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.function.Predicate;
import main.environment.Board;
import main.environment.BoardPosition;
import main.environment.Cell;

/**
 * Base class for representing Snakes.
 * Will be extended by HumanSnake and AutomaticSnake.
 * Common methods will be defined here.
 * 
 * @author luismota
 *
 */
public abstract class Snake extends Thread implements Serializable {
	private static final int DELTA_SIZE = 10;
	protected LinkedList<Cell> cells = new LinkedList<Cell>();
	protected int size = 1;
	private int id;
	private Board board;

	private int leftToIncrease = 0;

	// Constructors
	public Snake(int id, Board board) {
		super("[Snake - " + id + "]");
		this.id = id;
		this.board = board;
	}

	// Getters
	public int getIdentification() {
		return id;
	}

	public int getSize() {
		return size;
	}

	public int getLength() {
		return cells.size();
	}

	public LinkedList<Cell> getCells() {
		return cells;
	}

	public LinkedList<BoardPosition> getPath() {
		LinkedList<BoardPosition> coordinates = new LinkedList<BoardPosition>();
		for (Cell cell : cells) {
			coordinates.add(cell.getPosition());
		}

		return coordinates;
	}

	public LinkedList<BoardPosition> getPath(Predicate<Cell> filtro) {
		LinkedList<BoardPosition> coordinates = new LinkedList<BoardPosition>();
		for (Cell cell : cells)
			if (filtro.test(cell))
				coordinates.add(cell.getPosition());

		return coordinates;
	}

	public Board getBoard() {
		return board;
	}

	protected BoardPosition getHeadPosition() {
		return cells.getLast().getPosition();
	}

	protected Cell getHeadCell() {
		return cells.getLast();
	}

	// Move
	/**
	 * 
	 * @param cell - Next cell to be the snake's head.
	 * @throws InterruptedException
	 */
	protected void move(Cell cell) throws InterruptedException {
		// Add Snake's Head
		if (!cell.request(this))
			return; // Happens when HumanSnake tries to move to an ocupied Position
		cells.add(cell);

		if (leftToIncrease > 0)
			leftToIncrease--; // Don't clear tail to increase
		else
			cells.removeFirst().release(); // Clear Tail

		// Attempt to capture Goal
		captureGoal(cell);

		// Repaint
		board.setChanged();

		// Interval between moves
		sleep(Board.PLAYER_PLAY_INTERVAL);
	}

	// Capture Goal
	private void captureGoal(Cell cell) {
		if (!cell.isOcupiedByGoal())
			return;

		Goal goal = cell.getGoal();
		leftToIncrease += goal.captureGoal();
		cell.removeGoal();

		// Update Snake Size
		size += leftToIncrease;

	}

	// Auxiliar Functions
	protected void doInitialPositioning() {
		// Random position on the first column.
		// At startup, snake occupies a single cell
		BoardPosition at;
		do {
			at = generateInitiaBoardPosition();
		} while (board.getCell(at).isOcupiedBySnakeOrObstacle());

		try {
			board.getCell(at).request(this);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		cells.add(board.getCell(at));
		System.err.println("Snake " + getIdentification() + " starting at:" + getCells().getLast());
	}

	private BoardPosition generateInitiaBoardPosition() {
		return new BoardPosition(0, (int) (Math.random() * Board.NUM_ROWS));
	}

	// Object class
	@Override
	public String toString() {
		return getName();
	}
}
