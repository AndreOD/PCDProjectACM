package main.remote;


import main.environment.Board;
import main.environment.BoardPosition;
import main.environment.Cell;
import main.environment.LocalBoard;
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
    private RemoteGui gui;

    private PrintWriter outputStream; //Enviar keys por canais de texto
    private ObjectInputStream inputStream;
    private Socket connection;


    public Client(InetAddress serverAddress, int serverPort) {
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
        gui = new RemoteGui(board);
        runClient();
    }

    private void startClientGui() {
        gui.startGui();
    }


    public void runClient() {
        try {
            connection = new Socket(serverAddress, serverPort);
            getStreams();
            startClientGui();
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
        board.setKeyOutputStream(outputStream);
    }

    private void getBoardUpdates() throws IOException, ClassNotFoundException {
        Board updatedBoard;
        do{
            updatedBoard = (Board) inputStream.readObject();
            board.setCells(updatedBoard.getCells());
            board.setSnakes(updatedBoard.getSnakes());
            board.setChanged();
        }while (!updatedBoard.isFinished());

    }

    private void closeConnections() throws IOException {
        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
        connection.close();
    }


    public static void main(String[] args) throws UnknownHostException {
        InetAddress serverAddress = InetAddress.getByName(args[0]);
        Integer port = Integer.parseInt(args[1]);

        Client client = new Client(serverAddress, port);
        //TODO
    }

}
