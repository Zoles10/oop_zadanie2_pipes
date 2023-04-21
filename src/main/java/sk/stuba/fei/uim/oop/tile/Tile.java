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
    private final int posY;
    @Setter
    @Getter
    protected TileState currentTileState;
    @Setter
    @Getter
    private boolean visited;
    protected int tileSize;
    protected Random rand;
    @Setter
    @Getter
    private boolean highlight;
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
    protected void paintLineLeft(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, tileSize/4, tileSize / 2, tileSize / 2);
    }

    protected void paintLineRight(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(tileSize / 4, tileSize/4, tileSize , tileSize / 2);
    }

    protected void paintLineTop(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(tileSize / 4, 0, tileSize / 2, tileSize / 2 + tileSize / 4);
    }

    protected void paintLineBottom(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(tileSize / 4, tileSize / 4, tileSize / 2, tileSize );
    }

    protected void paintWaterLeft(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0, tileSize * 3 / 8, tileSize * 5 / 8, tileSize / 4);
    }

    protected void paintWaterRight(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(tileSize * 3 / 8, tileSize * 3 / 8, tileSize , tileSize / 4);
    }

    protected void paintWaterTop(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(tileSize * 3 / 8, 0, tileSize / 4, tileSize * 5 / 8);
    }

    protected void paintWaterBottom(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(tileSize * 3 / 8, tileSize * 3 / 8, tileSize / 4, tileSize);
    }

}
