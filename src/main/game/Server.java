package main.game;

import main.environment.Board;
import main.environment.BoardPosition;
import main.environment.LocalBoard;
import main.gui.SnakeGui;
import main.remote.GameState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class Server {

    public final static  int MILLISECONDS_TO_JOIN_BEFORE_GAME = 10000;
    protected final static int PORT = 12345; // Random port
    private ServerSocket serverSocket;
    private Board board = new LocalBoard();



    public void startServer(){
        try{
            serverSocket = new ServerSocket(PORT,10);
            initBoardAfterAWhile();
            waitForConnections();
        }catch (IOException e){

        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {}
        }
    }

    private void initBoardAfterAWhile() {
        new Thread(() -> {
            try {
                Thread.sleep(MILLISECONDS_TO_JOIN_BEFORE_GAME);
                SnakeGui localGui = new SnakeGui(board,600,0);
                localGui.init();
            } catch (InterruptedException e) {}
        }).start();
    }

    private void waitForConnections() throws IOException {
        while (true){
            Socket newConnection = serverSocket.accept();
            new ServerConnectionHandler(newConnection).start();
        }
    }

    private class ServerConnectionHandler extends Thread{
        private static AtomicInteger IDGENERATOR = new AtomicInteger(0);
        private Socket connection;
        private ObjectOutputStream out;
        private int id;
        private Thread inputReader;
        private Scanner in;

        private HumanSnake snake; //snake controlled by this client

        ServerConnectionHandler(Socket connection){
            this.id = IDGENERATOR.getAndIncrement();
            this.connection = connection;
        }

        @Override
        public void run() {
            try{
                System.out.println("New Client number " +id+" Connected");
                getStreams();
                createSnake();
                sendBoard();
            }catch (IOException | InterruptedException e){
            }finally {
                try {
                    closeConnections();
                } catch (IOException e) {}
            }
        }

        private void createSnake() {
            snake = new HumanSnake(id,board);
            board.addSnake(snake);
            snake.start();
            getKeyBoardInput(snake);
        }

        private void getKeyBoardInput(HumanSnake snake) {
            inputReader = new Thread(()->{
                while (!interrupted()){
                    if (in.hasNext())  snake.setDirection(in.nextLine());
                }
            });
            inputReader.start();
        }

        private void sendBoard() throws IOException, InterruptedException {
            while (true){
                out.reset();
                out.writeObject(board);
                sleep(Board.REMOTE_REFRESH_INTERVAL);
            }
        }

        private void getStreams() throws IOException {
            in = new Scanner(connection.getInputStream());
            out = new ObjectOutputStream(connection.getOutputStream());

        }
        private void closeConnections() throws IOException {
            inputReader.interrupt();
            System.err.println("Client " + id + " has Disconnected! Closing Socket!");
            if (out != null) out.close();
            if (in != null ) in.close();
            connection.close();
        }
    }

    // TODO



}
