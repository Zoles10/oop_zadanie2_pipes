package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.playarea.GameBoard;

import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Game{

    private GameBoard board;
    int boardSize;

    Game(){
        boardSize=8;
        this.board = new GameBoard(boardSize);

        JFrame frame = new JFrame();
        frame.setTitle("Water Pipes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout());
        JSlider slider = new JSlider(8, 12, 10);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        JLabel label = new JLabel(String.valueOf(slider.getValue()), JLabel.CENTER);
        label.setPreferredSize(new Dimension(50, 20));

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(8, new JLabel("8"));
        labelTable.put(10, new JLabel("10"));
        labelTable.put(12, new JLabel("12"));
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.addChangeListener(e -> {
            boardSize = slider.getValue();
            GameBoard newGameBoard = new GameBoard(boardSize);
            Container contentPane = frame.getContentPane();
            contentPane.remove(board);
            contentPane.add(newGameBoard, BorderLayout.CENTER);
            board = newGameBoard;
            board.createPath(0,board.getStart(),board.getBoardSize()-1,board.getEnd());
            frame.pack();
            board.repaint();
        });

        JButton button2 = new JButton("RESET");
        JButton button3 = new JButton("Check win");
        sidePanel.add(button2);
        sidePanel.add(button3);

        button2.addActionListener(e -> {
            GameBoard newGameBoard = new GameBoard(boardSize);
            Container contentPane = frame.getContentPane();
            contentPane.remove(board);
            contentPane.add(newGameBoard, BorderLayout.CENTER);
            board = newGameBoard;
            board.createPath(0,board.getStart(),board.getBoardSize()-1,board.getEnd());
            frame.pack();
            board.repaint();

        });

        button3.addActionListener(e -> {
            if(board.checkWin()){
                System.out.printf("YOU WIN");
            }
            else{
                System.out.println("NOT CORRECT!");
            }

        });


        board.createPath(0,board.getStart(),board.getBoardSize()-1,board.getEnd());
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(sidePanel, BorderLayout.NORTH);
        frame.getContentPane().add(slider, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        board.repaint();
    }






}
