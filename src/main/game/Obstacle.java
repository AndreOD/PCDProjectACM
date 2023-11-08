package main.game;

import main.environment.Board;
import main.environment.Cell;
import main.environment.LocalBoard;

import java.util.List;

public class Obstacle extends GameElement {
    private static final int NUM_MOVES = 3;
    private static final int OBSTACLE_MOVE_INTERVAL = 400;
    private int remainingMoves = NUM_MOVES;
    private Board board;

    // Constructors
    public Obstacle(Board board) {
        super();
        this.board = board;
    }

    // Getters
    public int getRemainingMoves() {
        return remainingMoves;
    }


    // Setters
    public void decrementRemainMovesAndAdd() {
        remainingMoves--;
        board.addGameElement(this);
        board.setChanged();
    }

    public void move() {

        try {
            while (remainingMoves > 0) {

                removeObstacle();
                decrementRemainMovesAndAdd();
                Thread.sleep(OBSTACLE_MOVE_INTERVAL);
            }
        } catch (InterruptedException e) {
        }
    }


    private void removeObstacle() {
        Cell[][] cells = board.getCells();
        for (Cell[] list : cells) {
            for (Cell c : list) {
                if (c.getObstacle() == this) {
                    c.removeObstacle();
                    return;
                }
            }
        }
    }


}
