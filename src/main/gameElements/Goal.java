package main.gameElements;

public class Goal extends GameElement {

    public static final int MAX_VALUE = 9;

    private static Goal INSTANCE = null;
    private int value = 0;

    // Singleton
    public static Goal getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Goal();

        return INSTANCE;
    }

    private Goal() {
        super(GameElement.getRandMove(), "Goal");
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
