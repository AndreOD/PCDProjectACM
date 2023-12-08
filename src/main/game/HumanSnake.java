package main.game;

import main.environment.Board;
import main.environment.BoardPosition;
import main.environment.Direction;

import java.util.Scanner;

/** Class for a remote snake, controlled by a human
  * 
  * @author luismota
  *
  */
public class HumanSnake extends Snake {

	private transient Direction direction = Direction.NULL;
	private transient Scanner in;

  // Constructors
	public HumanSnake(int id, Board board, Scanner in) {
		super(id,board);
		this.in = in;
	}

	 @Override
	 public void run() {
		 doInitialPositioning();
		 System.err.println(super.toString() + " initial size:" + cells.size());

		 while (!getBoard().isFinished()) {
			 try {
//				 if (in.hasNext())
//					 setDirection(in.nextLine());
				 BoardPosition nextMove = nextMove();

				 //Print do deus
				 System.err.println(getIdentification() + " trying to move to " + nextMove);
				 //Deus

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
			case "R":
				direction = Direction.RIGHT;
		}
	}

	private BoardPosition nextMove(){
		return getHeadPosition().plus(direction.getVector());
	}


}
