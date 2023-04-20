package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tile extends JPanel {
    @Getter
    protected final int posX;
    @Getter
    protected final int posY;
    @Setter
    @Getter
    protected TileState currentTileState;
    @Setter
    @Getter
    protected boolean visited;
    protected int tileSize;
    protected Random rand;
    @Setter
    @Getter
    protected boolean highlight;
    @Setter
    @Getter
    protected boolean correctPosition;
    @Getter
    protected PipeDirections pipeDirections;

    public Tile(int y, int x, int boardSize) {
        this.posX = x;
        this.posY = y;
        this.rand = new Random();
        this.highlight = false;
        this.correctPosition = false;
        this.tileSize = 800 / boardSize;
        this.pipeDirections = new PipeDirections();
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tileSize, tileSize);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (highlight) {
            this.setBackground(Color.LIGHT_GRAY);
        } else  {
            this.setBackground(Color.WHITE);
        }
    }

    public void switchCurrentTileState() {
        this.currentTileState = TileState.EMPTY;
    }

    public void setCurrentTileStateRandom() {
        this.currentTileState = TileState.EMPTY;
    }


}
