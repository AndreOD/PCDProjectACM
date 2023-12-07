package main.environment;

public enum Direction {

    /**
     * Used for Human Snake Movement
     */
    UP(new BoardPosition(0,-1)),
    DOWN(new BoardPosition(0,1)),
    RIGHT(new BoardPosition(1,0)),
    LEFT(new BoardPosition(-1,0)),
    NULL(new BoardPosition(0,0));

    private BoardPosition vector;
    Direction(BoardPosition dir){
        vector = dir;
    }

    public BoardPosition getVector() {
        return vector;
    }
    @Override
    public String toString(){
        return vector.toString();
    }
}
