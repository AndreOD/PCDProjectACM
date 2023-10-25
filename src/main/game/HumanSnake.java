package main.game;

import main.environment.Board;
 /** Class for a remote snake, controlled by a human 
  * 
  * @author luismota
  *
  */
public abstract class HumanSnake extends Snake {
	
  // Constructors
	public HumanSnake(int id,Board board) {
		super(id,board);
	}

}
