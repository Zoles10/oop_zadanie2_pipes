package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    protected ArrayList<Directions> directions;

    public Tile(int y, int x, int boardSize) {
        this.posX = x;
        this.posY = y;
        this.rand = new Random();
        this.highlight = false;
        this.correctPosition = false;
        this.tileSize = 800 / boardSize;
        this.directions = new ArrayList<>();
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
        } else {
            this.setBackground(Color.WHITE);
        }
    }

    public void setDirections(TileState tileState) {
        directions.clear();
        if (tileState.equals(TileState.I_PIPE_LEFT_RIGHT)) {
            directions.add(Directions.LEFT);
            directions.add(Directions.RIGHT);
            System.out.println(directions.size());
        } else if (tileState.equals(TileState.I_PIPE_TOP_DOWN)) {
            directions.add(Directions.TOP);
            directions.add(Directions.BOTTOM);
        } else if (tileState.equals(TileState.L_PIPE_DOWN_LEFT)) {
            directions.add(Directions.BOTTOM);
            directions.add(Directions.LEFT);
        } else if (tileState.equals(TileState.L_PIPE_DOWN_RIGHT)) {
            directions.add(Directions.BOTTOM);
            directions.add(Directions.RIGHT);
        } else if (tileState.equals(TileState.L_PIPE_TOP_RIGHT)) {
            directions.add(Directions.TOP);
            directions.add(Directions.RIGHT);
        } else if (tileState.equals(TileState.L_PIPE_TOP_LEFT)) {
            directions.add(Directions.TOP);
            directions.add(Directions.LEFT);
        } else if (tileState.equals(TileState.VALVE_TOP)) {
            directions.add(Directions.TOP);
        } else if (tileState.equals(TileState.VALVE_BOTTOM)) {
            directions.add(Directions.BOTTOM);
        } else if (tileState.equals(TileState.VALVE_RIGHT)) {
            directions.add(Directions.RIGHT);
        } else if (tileState.equals(TileState.VALVE_LEFT)) {
            directions.add(Directions.LEFT);
        }
    }

    public void switchCurrentTileState() {
        this.currentTileState = TileState.EMPTY;
    }

    public void setCurrentTileStateRandom() {
        this.currentTileState = TileState.EMPTY;
    }

    protected void paintLineLeft(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, tileSize / 4, tileSize / 2, tileSize / 2);
    }

    protected void paintLineRight(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(tileSize / 4, tileSize / 4, tileSize, tileSize / 2);
    }

    protected void paintLineTop(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(tileSize / 4, 0, tileSize / 2, tileSize / 2 + tileSize / 4);
    }

    protected void paintLineBottom(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(tileSize / 4, tileSize / 4, tileSize / 2, tileSize);
    }

    protected void paintWaterLeft(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, tileSize * 3 / 8, tileSize * 5 / 8, tileSize / 4);
    }

    protected void paintWaterRight(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(tileSize * 3 / 8, tileSize * 3 / 8, tileSize, tileSize / 4);
    }

    protected void paintWaterTop(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(tileSize * 3 / 8, 0, tileSize / 4, tileSize * 5 / 8);
    }

    protected void paintWaterBottom(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(tileSize * 3 / 8, tileSize * 3 / 8, tileSize / 4, tileSize);
    }
}
