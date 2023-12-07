package main.environment;

import java.io.Serializable;

import javax.sound.midi.SysexMessage;

import main.game.*;

/**
 * Main class for game representation.
 *
 * @author luismota
 */
public class Cell implements Serializable {
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

    public Obstacle getObstacle() {
        if (! (gameElement instanceof Obstacle)) return null;
        return (Obstacle) gameElement;
    }

    // Setters
    public synchronized boolean setGameElement(GameElement element) {
        if(isOcupiedBySnakeOrObstacle() || isOcupiedByGoal())
            return false;
        gameElement = element;
        return true;
    }

    public synchronized boolean request(Snake snake) throws InterruptedException {
        if (snake instanceof HumanSnake && isOcupiedBySnakeOrObstacle())
            return false;
        while (snake instanceof AutomaticSnake && isOcupiedBySnakeOrObstacle())
            wait(); // TODO change wait to increase performance.
        ocuppyingSnake = snake;
        return true;
    }

    public synchronized void release() {
        ocuppyingSnake = null;
        notifyAll();
    }

    // Removes

    /**
     * Removes goal from current Cell.<p>
     * Does not verify if current Cell has Goal.
     *
     * @return {@code Goal removed} in the current Cell.
     * @see #isOcupiedByGoal()
     */
    public Goal removeGoal() {
        Goal g = (Goal) gameElement;
        gameElement = null;
        return g;
    }

    public synchronized void removeObstacle() {
        gameElement = null;
        notifyAll();
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

    // Object class
    @Override
    public String toString() {
        return "[BoardPosition: " + position + " | " + (isOcupiedBySnake() ? ocuppyingSnake : gameElement) + " ]";
    }
}
