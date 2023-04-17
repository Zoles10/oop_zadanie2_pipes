package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.GameLogic;

import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;

public class Game {
    public Game() {
        JFrame frame = new JFrame("Water Pipes : The Action Game ");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton resetButton = new JButton("RESET");
        JButton checkWinButton = new JButton("Check win");

        GameLogic logic = new GameLogic(frame, resetButton, checkWinButton);

        resetButton.addActionListener(logic);
        checkWinButton.addActionListener(logic);

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

        JPanel labelPanel = new JPanel();
        labelPanel.add(logic.getLevelLabel());
        labelPanel.add(logic.getBoardSizeLabel());
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(2, 2));
        sidePanel.add(labelPanel);
        sidePanel.add(slider);
        sidePanel.add(checkWinButton);
        sidePanel.add(resetButton);

        frame.addKeyListener(logic);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setResizable(false);
        frame.add(sidePanel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }
}
