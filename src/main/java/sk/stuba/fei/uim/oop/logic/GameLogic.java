package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;

import sk.stuba.fei.uim.oop.board.GameBoard;
import sk.stuba.fei.uim.oop.tile.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private Tile lastHighlightedTile;
    private final JFrame frame;
    private GameBoard gameBoard;
    @Getter
    private final JLabel levelLabel;
    @Getter
    private final JLabel boardSizeLabel;
    private int level;
    private final JButton resetButton;
    private final JButton checkWinButton;
    @Getter
    private int currentBoardSize;

    public GameLogic(JFrame frame, JButton resetButton, JButton checkWinButton) {
        this.frame = frame;
        this.resetButton = resetButton;
        this.checkWinButton = checkWinButton;
        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.initializeNewBoard(this.currentBoardSize);
        this.frame.add(this.gameBoard, BorderLayout.CENTER);
        this.level = 1;
        this.levelLabel = new JLabel();
        this.boardSizeLabel = new JLabel();
        this.levelLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.boardSizeLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.setBoardSizeLabel();
        this.setLevelLabelText();
    }

    private void setLevelLabelText() {
        this.levelLabel.setText("Level: " + level);
    }

    private void setBoardSizeLabel() {
        this.boardSizeLabel.setText("Board size: " + getCurrentBoardSize() + "x" + getCurrentBoardSize());
    }

    private void gameRestart() {
        this.frame.remove(this.gameBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.frame.add(this.gameBoard, BorderLayout.CENTER);
        setLevelLabelText();
        setBoardSizeLabel();
        this.frame.revalidate();
        this.frame.repaint();
    }

    private void initializeNewBoard(int size) {
        this.gameBoard = new GameBoard(size);
        this.gameBoard.addMouseMotionListener(this);
        this.gameBoard.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            this.level = 1;
            this.gameRestart();
        } else if (e.getSource() == checkWinButton) {
            checkWin();
        }
        this.frame.revalidate();
        this.frame.repaint();
        this.frame.setFocusable(true);
        this.frame.requestFocusInWindow();
    }

    private void checkWin() {
        if (lastHighlightedTile != null) {
            this.lastHighlightedTile.setHighlight(false);
        }
        if (gameBoard.checkWin()) {
            String[] options = {"Continue", "Quit"};
            int choice = JOptionPane.showOptionDialog(frame, "You completed the level! Do you wish to continue to the next one, or quit playing?", "Level Complete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (choice == JOptionPane.YES_OPTION) {
                level++;
                gameRestart();
            } else if (choice == JOptionPane.NO_OPTION) {
                this.frame.dispose();
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Path is not correct! Try again!", "Incorrect Path", JOptionPane.ERROR_MESSAGE);
            this.gameBoard.resetCorrectPosition();
            this.gameBoard.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component current = this.gameBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile) || current instanceof Valve) {
            return;
        }
        ((Tile) current).switchCurrentTileState();
        current.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.gameBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile)) {
            if (lastHighlightedTile != null) {
                lastHighlightedTile.setHighlight(false);
            }
            return;
        }
        if (lastHighlightedTile == null) {
            lastHighlightedTile = ((Tile) current);
            lastHighlightedTile.setHighlight(true);
            return;
        }
        if (!lastHighlightedTile.equals(current)) {
            lastHighlightedTile.setHighlight(false);
            lastHighlightedTile = ((Tile) current);
            lastHighlightedTile.setHighlight(true);
            return;
        }
        lastHighlightedTile.setHighlight(true);
        this.gameBoard.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (lastHighlightedTile != null) {
            lastHighlightedTile.setHighlight(false);
            lastHighlightedTile = null;
            this.gameBoard.repaint();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            return;
        }
        currentBoardSize = source.getValue();
        this.level = 1;
        if (lastHighlightedTile != null) {
            this.lastHighlightedTile.setHighlight(false);
        }
        this.gameRestart();
        this.frame.setFocusable(true);
        this.frame.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                level = 1;
                this.gameRestart();
                break;
            case KeyEvent.VK_ESCAPE:
                this.frame.dispose();
                System.exit(0);
                break;
            case KeyEvent.VK_ENTER:
                this.checkWin();
                break;
        }
    }

}