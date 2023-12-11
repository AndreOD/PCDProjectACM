package main.gui;

import main.environment.LocalBoard;
import main.game.Server;

public class Main {
	private static final boolean FINALPHASE = true;

	public static void mainPhaseTwo(){
		Server server = new Server();
		server.startServer();
	}

	public static void main(String[] args) {
		if (FINALPHASE)
			mainPhaseTwo();
		else
			mainPhaseOne();
	}

	public static void mainPhaseOne(){
		LocalBoard board=new LocalBoard();
		SnakeGui game = new SnakeGui(board,600,0);
		game.init();
	}


}
