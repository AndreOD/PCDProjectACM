package main.remote;

import main.gui.BoardComponent;
import main.gui.SnakeGui;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class RemoteGui implements Observer {


    private JFrame frame;
    private BoardComponent boardGui;
    private RemoteBoard board;

    public RemoteGui(RemoteBoard board) {
        this.board = board;
        setupFrame();
    }

    private void setupFrame() {
        frame = new JFrame("Remote Cliente SnakeGame");
        frame.setLayout(new BorderLayout());
        boardGui = new BoardComponent(board);
        boardGui.setPreferredSize(new Dimension(SnakeGui.BOARD_WIDTH, SnakeGui.BOARD_HEIGHT));
        frame.add(boardGui, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startGui(){
        board.addObserver(this);
        board.init();
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        boardGui.repaint();
    }
}
