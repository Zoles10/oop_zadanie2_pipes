package sk.stuba.fei.uim.oop;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Grid extends JPanel {

    int size;
    Grid(int num){
        this.size = num;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 50;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(x, y, cellSize, cellSize);
                System.out.println(x);
            }
        }
    }


}
