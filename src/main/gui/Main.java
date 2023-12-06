package main.gui;

import java.io.Console;
import java.io.IOException;

import javax.net.ssl.StandardConstants;

import main.environment.LocalBoard;
import main.game.Server;

public class Main {
	public static void main(String[] args) {
//		LocalBoard board=new LocalBoard();
//		SnakeGui game = new SnakeGui(board,600,0);  jogo local
//		game.init();
		Server server = new Server();
		server.startServer();
		// Launch server
		// TODO
		
	}
}
