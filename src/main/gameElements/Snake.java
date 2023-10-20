package main.gameElements;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import main.board.Board;

// TODO pintar blocos da snake
public class Snake extends GameElement implements Unsurpassable {

    List<Point2D> body = new ArrayList<>();

    Snake(Point2D position, int id) {
        super(position, "Snake - " + id);
    }

    void move() throws InterruptedException {
        Goal goal = Goal.getInstance();
        Point2D goalPostion = goal.getPostion();

        // TODO POO Project Code? *Change position*

        // Verify if is valid move move
        while (!Board.isValid(getPostion()))
            wait();

        // Catch Goal
        if (getPostion().equals(goalPostion)) {
            int valueGoal = goal.captureGoal();
            increase(valueGoal);
        }
    }

    void updateBodyPostions() {
        for (Point2D point2d : body) {
            // TODO Paint
        }
    }

    // TODO
    private void increase(int numberOfBlocks) {

    }

    // TODO
    @Override
    public void run() {
    }
}
