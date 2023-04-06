package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Tile extends JPanel {
    @Getter
    protected final int posX;
    @Getter
    protected final int posY;
    @Setter
    @Getter
    protected TileState correctTileState;
    @Setter
    @Getter
    protected TileState currentTileState;
    @Setter
    @Getter
    protected boolean visited;
    protected int tileSize;
    Random rand;
    @Setter
    @Getter
    protected boolean highlight;
    @Setter
    @Getter
    protected boolean correctPosition;

    public Tile(int y, int x, int boardSize) {
        this.posX = x;
        this.posY = y;
        this.rand = new Random();
        this.highlight = false;
        this.correctPosition = false;
        tileSize = 800 / boardSize;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tileSize, tileSize);
    }

    public boolean checkCorrectShape() {
        return correctTileState == currentTileState;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.currentTileState.equals(TileState.L_PIPE_DOWN_RIGHT)) {
            g.fillRect(20, 20, tileSize / 2, tileSize);
            g.fillRect(20, 20, tileSize, tileSize / 2);
        } else if (this.currentTileState.equals(TileState.L_PIPE_DOWN_LEFT)) {
            g.fillRect(20, 20, tileSize / 2, tileSize);
            g.fillRect(0, 20, tileSize / 2, tileSize / 2);
        } else if (this.currentTileState.equals(TileState.L_PIPE_TOP_RIGHT)) {
            g.fillRect(20, 0, tileSize / 2, tileSize / 2);
            g.fillRect(20, 20, tileSize, tileSize / 2);
        } else if (this.currentTileState.equals(TileState.L_PIPE_TOP_LEFT)) {
            g.fillRect(20, 0, tileSize / 2, tileSize / 2);
            g.fillRect(0, 20, tileSize / 2 + 20, tileSize / 2);
        } else if (this.currentTileState.equals(TileState.I_PIPE_TOP_DOWN)) {
            g.fillRect(20, 0, tileSize / 2, tileSize);
        } else if (this.currentTileState.equals(TileState.I_PIPE_LEFT_RIGHT)) {
            g.fillRect(0, 20, tileSize, tileSize / 2);
        } else if (this.currentTileState.equals(TileState.VALVE)) {
            g.setColor(Color.GREEN);
            g.fillRect(20, 20, tileSize / 2, tileSize / 2);
        }

        if (highlight) {
            this.setBackground(Color.LIGHT_GRAY);
        } else {
            if (correctPosition) {
                this.setBackground(Color.GREEN);
            } else {
                this.setBackground(Color.WHITE);
            }
        }
    }

    public abstract void switchCurrentTileState();

    public abstract void setCurrentTileStateRandom();
}
