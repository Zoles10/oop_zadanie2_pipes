package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.GameLogic;

import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;

public class Game{

    int boardSize;

    public Game(){

        boardSize=8;

        JFrame frame = new JFrame("Water Pipes : The Action Game ");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton resetButton = new JButton("RESET");
        JButton checkWinButton = new JButton("Check win");
        GameLogic logic = new GameLogic(frame,resetButton,checkWinButton);
        resetButton.addActionListener(logic);
        checkWinButton.addActionListener(logic);
        frame.addKeyListener(logic);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(2, 2));
        frame.setResizable(false);
        JSlider slider = new JSlider(8, 12, 8);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);



        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(8, new JLabel("8"));
        labelTable.put(10, new JLabel("10"));
        labelTable.put(12, new JLabel("12"));
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.addChangeListener(logic);
        sidePanel.add(logic.getLevelLabel());
        sidePanel.add(resetButton);
        sidePanel.add(checkWinButton);

        sidePanel.add(slider);
        frame.add(sidePanel,BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

    }






}
