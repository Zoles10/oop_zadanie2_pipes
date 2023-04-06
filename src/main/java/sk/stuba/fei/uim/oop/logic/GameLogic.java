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

    private Tile lastHighlightedTile;
    public static final int INITIAL_BOARD_SIZE = 8;
    private final JFrame frame;
    private GameBoard gameBoard;
    @Getter
    private final JLabel levelLabel;
    private int level;

    JButton resetButton;
    JButton checkWinButton;
    @Getter
    private int currentBoardSize;

    public GameLogic(JFrame frame, JButton resetButton, JButton checkWinButton) {
        this.frame = frame;
        this.resetButton = resetButton;
        this.checkWinButton = checkWinButton;
        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.initializeNewBoard(this.currentBoardSize);
        this.frame.add( this.gameBoard,BorderLayout.CENTER);
        this.level = 1;
        this.levelLabel = new JLabel();
        setLevelLabelText();
//        this.gameBoard.setFocusable(true);
//        this.gameBoard.requestFocusInWindow();
    }

    private void setLevelLabelText(){
        this.levelLabel.setText("Level "+level);
    }


    private void gameRestart() {
        this.frame.remove(this.gameBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.frame.add( this.gameBoard,BorderLayout.CENTER);
        setLevelLabelText();
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
    }

    public void checkWin(){
        if(gameBoard.checkWin()){
            JOptionPane.showMessageDialog(null, "YOU WIN!");
            level++;
            gameRestart();
        }
        else{
            JOptionPane.showMessageDialog(null, "TRY HARDER");
        }
        this.lastHighlightedTile.setHighlight(false);

    }
    public void mousePressed(MouseEvent e) {
        Component current = this.gameBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile)) {
            return;
        }

        if(!((Tile) current).isCorrectPosition()) {
            ((Tile) current).switchCurrentTileState();
            current.repaint();
        }
}

    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.gameBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Tile)) {
            return;
        }

        if(lastHighlightedTile==null){
            lastHighlightedTile = ((Tile) current);
            lastHighlightedTile.setHighlight(true);
            return;
        }

        if(!lastHighlightedTile.equals(current)){
            lastHighlightedTile.setHighlight(false);
            lastHighlightedTile = ((Tile) current);
            lastHighlightedTile.setHighlight(true);
            return;
        }

        lastHighlightedTile.setHighlight(true);
//        System.out.println("TILE ["+((Tile) current).getX()+","+((Tile) current).getY()+"]");
        this.gameBoard.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.currentBoardSize = ((JSlider) e.getSource()).getValue();
        this.level = 1;
        this.lastHighlightedTile.setHighlight(false);
        this.gameRestart();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.gameRestart();
                level=1;
                break;
            case KeyEvent.VK_ESCAPE:
                this.frame.dispose();
            case KeyEvent.VK_ENTER:
                this.checkWin();
        }
    }

}