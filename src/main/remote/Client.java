package main.remote;


import main.environment.Board;
import main.environment.Cell;
import main.game.Server;
import main.gui.SnakeGui;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Remore client, only for part II
 *
 * @author luismota
 */

public class Client {

    //Client vai ter um object RemoteBoard que irá representar o estado do jogo

    private int serverPort;
    private InetAddress serverAddress;
    private RemoteBoard board = new RemoteBoard();


	public Client(InetAddress serverAddress, int serverPort) {
		this.serverPort = serverPort;
		this.serverAddress = serverAddress;
		startClientGui();
		new ClientConnectionHandler().start();
	}

	private void startClientGui() {
		SnakeGui clientGui = new SnakeGui(board,600,0);
		clientGui.init();
		//TODO
	}

    private void updateBoard(){
        //TODO
        board.setChanged();
    }

	private class ClientConnectionHandler extends Thread { //Alterar
        private PrintWriter outputStream; //Enviar keys por canais de texto
        private ObjectInputStream inputStream;
        private Socket connection;

        @Override
        public void run() {
            try {
                connection = new Socket(serverAddress, serverPort);
                getStreams();
                System.out.println("h2");
                getBoardUpdates();
            } catch (IOException | ClassNotFoundException e) {
            } finally {
                try {
                    closeConnections();
                } catch (IOException e) {
                }
            }
        }

        private void getStreams() throws IOException {
            inputStream = new ObjectInputStream(connection.getInputStream());
            outputStream = new PrintWriter(connection.getOutputStream(), true);
        }

        private void getBoardUpdates() throws IOException, ClassNotFoundException {
            while (true) {
                Board newboard = (Board) inputStream.readObject();
                board.setCells(newboard.getCells());
                board.setSnakes(newboard.getSnakes());
                board.setChanged();
                System.out.println("Recebido");

				//TODO
            }
        }

        private void closeConnections() throws IOException {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            connection.close();
        }
    }


    public static void main(String[] args) throws UnknownHostException {
        InetAddress serverAddress = InetAddress.getByName(args[0]);
        Integer port = Integer.parseInt(args[1]);

		Client client = new Client(serverAddress,port);
        //TODO
    }

}
