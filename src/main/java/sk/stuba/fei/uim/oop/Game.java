package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.playarea.GameBoard;

import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;

public class Game{

    private GameBoard board;
    int boardSize;
    int level;

    Game(){
        level=1;

        boardSize=8;

        JFrame frame = new JFrame("Water Pipes : The Action Game ");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.board = new GameBoard(boardSize);




        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout());

        JSlider slider = new JSlider(8, 12, 10);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);

        JLabel label = new JLabel(String.valueOf(slider.getValue()), JLabel.CENTER);
        label.setPreferredSize(new Dimension(50, 20));
        JLabel levelLabel = new JLabel("Level: "+level);

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
            frame.pack();
            board.revalidate();
            board.repaint();
        });

        JButton reset = new JButton("RESET");
        JButton button3 = new JButton("Check win");
        sidePanel.add(reset);
        sidePanel.add(button3);
        sidePanel.add(levelLabel);

        reset.addActionListener(e -> {
            GameBoard newGameBoard = new GameBoard(boardSize);
            Container contentPane = frame.getContentPane();
            contentPane.remove(board);
            contentPane.add(newGameBoard, BorderLayout.CENTER);
            board = newGameBoard;
            frame.pack();
            level=1;
            levelLabel.setText("Level: " + level);
            board.repaint();

        });

        button3.addActionListener(e -> {
            if(board.checkWin()){

                JOptionPane.showMessageDialog(null, "YOU WIN!");
                GameBoard newGameBoard = new GameBoard(boardSize);
                Container contentPane = frame.getContentPane();
                contentPane.remove(board);
                contentPane.add(newGameBoard, BorderLayout.CENTER);
                board = newGameBoard;
                frame.pack();
                level++;
                levelLabel.setText("Level: " + level);

            }
            else{
                System.out.println("NOT CORRECT!");
                frame.pack();
            }
            board.repaint();
        });

        sidePanel.add(slider, BorderLayout.NORTH);
        frame.getContentPane().add(sidePanel, BorderLayout.NORTH);
        frame.getContentPane().add(board, BorderLayout.CENTER);

        frame.pack();
        board.repaint();
        frame.setVisible(true);

    }






}
