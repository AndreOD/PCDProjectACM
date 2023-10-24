package test.alex;

public class ObstacleAlex extends GameElementAlex implements UnsurpassableAlex {

    public static final int NUM_SIMULTANEOUS_MOVING_OBSTACLES = 0; // TODO_ThreadPool
    public static final int OBSTACLE_MOVE_INTERVAL = 0; // TODO_Put Value
    public static final int NUM_MOVES = 3;

    ObstacleAlex(int id) {
        super(GameElementAlex.getRandMove(), "Obstacle - " + id);
    }

    void move() throws InterruptedException {
        int numMoves = 0;
        while (numMoves < NUM_MOVES) {
            moveRand();
            sleep(OBSTACLE_MOVE_INTERVAL);
        }
    }

    // TODO Usar move, etc.
    @Override
    public void run() {
    }
}