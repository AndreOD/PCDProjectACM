package main.game;

import main.environment.Board;
import main.environment.BoardPosition;
import main.environment.Direction;

/** Class for a remote snake, controlled by a human
  * 
  * @author luismota
  *
  */
public class HumanSnake extends Snake {

	private Direction direction = Direction.NULL;


  // Constructors
	public HumanSnake(int id,Board board) {
		super(id,board);
	}

	 @Override
	 public void run() {
		 doInitialPositioning();
		 System.err.println(super.toString() + " initial size:" + cells.size());

		 while (!getBoard().isFinished()) {
			 try {
				 // System.err.println(toString() + " trying to move to " + nextMove());
				 BoardPosition nextMove = nextMove();
				 if (nextMove.x < 0 || nextMove.x >= Board.NUM_COLUMNS || nextMove.y < 0 || nextMove.y >= Board.NUM_ROWS )
					 continue; //ignore move
				 move(getBoard().getCell(nextMove()));
			 } catch (InterruptedException e) {}
		 }
	 }
	public void setDirection(String directionString){
		switch (directionString){
			case "U":
				direction = Direction.UP;
				break;
			case "D":
				direction = Direction.DOWN;
				break;
			case "L":
				direction = Direction.LEFT;
				break;
			default:
				direction = Direction.RIGHT;
		}
	}

	private BoardPosition nextMove(){
		return getHeadPosition().plus(direction.getVector());

	}


}
