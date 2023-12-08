package main.remote;

import java.awt.event.KeyEvent;
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

	//KeyCode sent Before the next one, determined to see if u need to resent the sameKeycode
	private int lastCode = -1;

	//Used to check if the user has pressed another key, turns to true in init and at key release
	private boolean canSwitch = false;

	private PrintWriter keyOutputStream;
	
	@Override
	public void handleKeyPress(int keyCode) {
		if (!canSwitch || lastCode == keyCode)return;
		keyOutputStream.println(getCharToSend(keyCode));
		lastCode = keyCode;
		canSwitch = false;
	}

	@Override
	public void handleKeyRelease() {
		canSwitch = true;
	}

	@Override
	public void init() {
		canSwitch = true;
	}


	public void setKeyOutputStream(PrintWriter outputStream){
		keyOutputStream = outputStream;
	}

	/**
	 * Client will send the Characters U(UP),D(DOWN),R(RIGHT),L(LEFT) to the server
	 * The Char 'N' is return when the client should not send anything to server
	 **/
	public char getCharToSend(int keyCodeToSend){
		switch (keyCodeToSend){
			case KeyEvent.VK_LEFT:
				return 'L';
			case KeyEvent.VK_UP:
				return 'U';
			case KeyEvent.VK_RIGHT:
				return 'R';
			case KeyEvent.VK_DOWN:
				return 'D';
			default:
				return 'N';
		}
	}



}
