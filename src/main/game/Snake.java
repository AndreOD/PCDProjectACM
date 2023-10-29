package main.game;

import java.io.Serializable;
import java.util.LinkedList;

import main.environment.LocalBoard;
import main.gui.SnakeGui;
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
	protected int size = 5;
	private int id;
	private Board board;

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

	public Board getBoard() {
		return board;
	}

	// Move
	/**
	 * 
	 * @param cell - Next cell to be the snake's head.
	 * @throws InterruptedException
	 */
	protected void move(Cell cell) throws InterruptedException {
		// Add Snake's Head
		cell.request(this);
		cells.add(cell);

		// Clear Tail
		cells.removeFirst().release();

		// Attempt to capture Goal
		captureGoal(cell);

		// Repaint
		board.setChanged();
	}

	// Capture Goal
	private void captureGoal(Cell cell) {
		if (!cell.isOcupiedByGoal())
			return;

		Goal g = cell.removeGoal();
		int valueToIncrease = g.captureGoal();// TODO Increase Snake
	}

	// Auxiliar Functions
	protected void doInitialPositioning() {
		// Random position on the first column.
		// At startup, snake occupies a single cell
		int posX = 0;
		int posY = (int) (Math.random() * Board.NUM_ROWS);
		BoardPosition at = new BoardPosition(posX, posY);

		try {
			board.getCell(at).request(this);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		cells.add(board.getCell(at));
		System.err.println("Snake " + getIdentification() + " starting at:" + getCells().getLast());
	}

	// Object class
	@Override
	public String toString() {
		return getName();
	}
}
