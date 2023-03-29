package sk.stuba.fei.uim.oop;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
public class GameBoard extends JFrame {

    GameBoard(int boardSize) {

       setTitle("My Frame");

       setSize(boardSize,boardSize);

       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       Grid grid = new Grid(boardSize/50);
       grid.setPreferredSize(new Dimension(boardSize, boardSize));
       getContentPane().add(grid);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Hello, world!");
        panel.add(label);
        getContentPane().add(panel);
       pack();

       setVisible(true);
    }
}
