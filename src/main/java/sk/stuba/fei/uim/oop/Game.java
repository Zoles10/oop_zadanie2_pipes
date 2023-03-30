package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.playarea.GameBoard;

import java.awt.*;
import javax.swing.*;

public class Game{

    private GameBoard board;

    Game(){
        this.board = new GameBoard(8);

        JFrame frame = new JFrame();
        frame.setTitle("Water Pipes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout());

        JButton button2 = new JButton("8x8");
        JButton button3 = new JButton("10x10");
        JButton button4 = new JButton("12x12");
        sidePanel.add(button2);
        sidePanel.add(button3);
        sidePanel.add(button4);

        button2.addActionListener(e -> {
            GameBoard newGameBoard = new GameBoard(8);
            Container contentPane = frame.getContentPane();
            contentPane.remove(board);
            contentPane.add(newGameBoard, BorderLayout.CENTER);
            board = newGameBoard;
            board.createPath(0,board.getStart(),board.getBoardSize()-1,board.getEnd());
            frame.pack();
            board.repaint();

        });

        button3.addActionListener(e -> {
            GameBoard newGameBoard = new GameBoard(10);
            Container contentPane = frame.getContentPane();
            contentPane.remove(board);
            contentPane.add(newGameBoard, BorderLayout.CENTER);
            board = newGameBoard;
            frame.pack();
            board.repaint();

        });

        button4.addActionListener(e -> {
            GameBoard newGameBoard = new GameBoard(12);
            Container contentPane = frame.getContentPane();
            contentPane.remove(board);
            contentPane.add(newGameBoard, BorderLayout.CENTER);
            board = newGameBoard;
            frame.pack();
            board.repaint();

        });

        board.createPath(0,board.getStart(),board.getBoardSize()-1,board.getEnd());
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(sidePanel, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
    }






}
