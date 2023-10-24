package test.alex;

public class GoalAlex extends GameElementAlex {

    public static final int MAX_VALUE = 9;

    private static GoalAlex INSTANCE = null;
    private int value = 0;

    // Singleton
    public static GoalAlex getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GoalAlex();

        return INSTANCE;
    }

    private GoalAlex() {
        super(GameElementAlex.getRandMove(), "Goal");
    }

    public int captureGoal() {
        if (value < MAX_VALUE) {
            value++;
            moveRand();
        } else {
            // TODO STOP GAME
        }

        return value;
    }

    // TODO
    @Override
    public void run() {
    }
}