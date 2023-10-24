package main.remote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.environment.LocalBoard;
import main.environment.Board;
import main.environment.BoardPosition;
import main.environment.Cell;
import main.game.Goal;
import main.game.Obstacle;
import main.game.Snake;

/** Remote representation of the game, no local threads involved.
 * Game state will be changed when updated info is received from Srver.
 * Only for part II of the project.
 * @author luismota
 *
 */
public class RemoteBoard extends Board{
	
	@Override
	public void handleKeyPress(int keyCode) {
		//TODO
	}

	@Override
	public void handleKeyRelease() {
		// TODO
	}

	@Override
	public void init() {
		// TODO 		
	}


	

}
