package main.remote;

import main.environment.Cell;
import main.game.Snake;

import java.io.Serializable;
import java.util.LinkedList;

public class GameState implements Serializable {
 //Classe a ser usada caso seja preciso
    private final Cell[][] cells;

    private final LinkedList<Snake> snakes;

    public GameState(Cell[][] cells, LinkedList<Snake> snakes) {
        this.cells = cells;
        this.snakes = snakes;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public LinkedList<Snake> getSnakes() {
        return snakes;
    }
}
