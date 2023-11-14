package main.game;

import main.environment.Board;

public class Obstacle extends GameElement {
    private static final int NUM_MOVES = 3;
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
    public void decrementRemainingMoves() {
        remainingMoves--;
    }

}
