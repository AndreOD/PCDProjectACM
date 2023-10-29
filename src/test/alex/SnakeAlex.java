package test.alex;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
public class SnakeAlex extends GameElementAlex implements UnsurpassableAlex{
    List<Point2D> body = new ArrayList<>();

    SnakeAlex(Point2D position, int id) {
        super(position, "Snake - " + id);
    }

    void move() throws InterruptedException {
        GoalAlex goal = GoalAlex.getInstance();
        Point2D goalPostion = goal.getPostion();

        // TODO POO Project Code? *Change position*

        // Verify if is valid move move
        // while (!BoardAlex.isValid(getPostion()))
        //     wait();

        // Catch GoalAlex
        if (getPostion().equals(goalPostion)) {
            int valueGoalAlex = goal.captureGoal();
            increase(valueGoalAlex);
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