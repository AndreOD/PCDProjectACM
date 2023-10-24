package test.alex;

import java.awt.geom.Point2D;

public abstract class GameElementAlex extends Thread {
    private Point2D position;

    GameElementAlex(Point2D position, String name) {
        super(name);
        this.position = position;
    }

    public Point2D getPostion() {
        return position;
    }

    protected void moveRand() {
        position = getRandMove();
    }

    // TODO Change with window size, obstacles and snakes
    static Point2D getRandMove() {
        return null;
    }
}