package main.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

import main.concurrent.BarrierTimeout;
import main.environment.Board;
import main.environment.LocalBoard;
import main.gui.SnakeGui;

public class Server {

    public final static int MILLISECONDS_TO_JOIN_BEFORE_GAME = 10000;
    protected final static int PORT = 12345; // Random port
    private ServerSocket serverSocket;
    private Board board = new LocalBoard();
    public BarrierTimeout barrier = BarrierTimeout.getInstance();

    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT, 10);
            initBoard();
            waitForConnections();
        } catch (IOException e) {

        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
        }
    }

    private void initBoard() {
        SnakeGui localGui = new SnakeGui(board, 600, 0);
        localGui.init();
        barrier.setTimeout(MILLISECONDS_TO_JOIN_BEFORE_GAME);
    }

    private void waitForConnections() throws IOException {
        while (true) {
            Socket newConnection = serverSocket.accept();
            ServerConnectionHandler svConect = new ServerConnectionHandler(newConnection);
            svConect.start();
        }
    }

    private class ServerConnectionHandler extends Thread {
        private static AtomicInteger IDGENERATOR = new AtomicInteger(0);
        private int id;
        private Socket connection;
        private ObjectOutputStream out;
        private BufferedReader in;

        private HumanSnake snake; // snake controlled by this client

        ServerConnectionHandler(Socket connection) {
            this.id = IDGENERATOR.getAndIncrement();
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                System.out.println("New Client number " + id + " Connected");
                getStreams();
                createSnake();
                sendBoard();
            } catch (IOException | InterruptedException e) {
            } finally {
                try {
                    closeConnections();
                } catch (IOException e) {
                }
            }
        }

        private void createSnake() {
            snake = new HumanSnake(id, board, in);
            board.addSnake(snake);
            snake.start();
        }

        private void sendBoard() throws IOException, InterruptedException {
            while (true) {
                out.reset();
                out.writeObject(board);
                sleep(Board.REMOTE_REFRESH_INTERVAL);
            }
        }

        private void getStreams() throws IOException {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new ObjectOutputStream(connection.getOutputStream());
        }

        private void closeConnections() throws IOException {
            System.err.println("Client " + id + " has Disconnected! Closing Socket!");
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            connection.close();
        }
    }

}
