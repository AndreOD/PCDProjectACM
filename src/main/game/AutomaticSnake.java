package main.game;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.Position;

import main.environment.LocalBoard;
import main.gui.SnakeGui;
import main.environment.Cell;
import main.environment.Board;
import main.environment.BoardPosition;

public class AutomaticSnake extends Snake {
	
	// Constructors
	public AutomaticSnake(int id, LocalBoard board) {
		super(id,board);
	}

	// Thread Class
	@Override
	public void run() {
		doInitialPositioning();
		System.err.println("initial size:"+cells.size());
		try {
			cells.getLast().request(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//TODO: automatic movement
	}
	

	
}
