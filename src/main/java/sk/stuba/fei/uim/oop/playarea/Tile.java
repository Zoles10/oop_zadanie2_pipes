package sk.stuba.fei.uim.oop.playarea;


import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private final int x;
    private final int y;
    private TileState tileState;
    private boolean visited;
    public static final int TILE_SIZE = 50;

    Tile(int y, int x) {
        this.x = x;
        this.y = y;
        this.tileState = TileState.EMPTY;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (this.tileState.equals(TileState.EMPTY)) {
            this.setBackground(Color.WHITE);
        } else if (this.tileState.equals(TileState.L_PIPE_DOWN_RIGHT)) {
            this.setBackground(Color.BLACK);
            g.fillRect(20, 20, TILE_SIZE/2, TILE_SIZE );
            g.fillRect(20 , 20, TILE_SIZE , TILE_SIZE/2);
        }else if (this.tileState.equals(TileState.L_PIPE_DOWN_LEFT)) {
            this.setBackground(Color.BLACK);
            g.fillRect(20, 20, TILE_SIZE/2, TILE_SIZE );
            g.fillRect(0, 20, TILE_SIZE, TILE_SIZE/2);
        }else if (this.tileState.equals(TileState.L_PIPE_TOP_RIGHT)) {
            this.setBackground(Color.BLACK);
            g.fillRect(20, 0, TILE_SIZE/2, TILE_SIZE);
            g.fillRect(20 , 20, TILE_SIZE , TILE_SIZE/2);
        }else if (this.tileState.equals(TileState.L_PIPE_TOP_LEFT)) {
            this.setBackground(Color.BLACK);
            g.fillRect(20, 0, TILE_SIZE/2, TILE_SIZE);
            g.fillRect(0, 20, TILE_SIZE, TILE_SIZE/2);
        }else if (this.tileState.equals(TileState.I_PIPE_TOP_DOWN)) {
            this.setBackground(Color.BLACK);
            g.fillRect(0, 0, TILE_SIZE/2, TILE_SIZE);
        }else if (this.tileState.equals(TileState.I_PIPE_LEFT_RIGHT)) {
            this.setBackground(Color.BLACK);
            g.fillRect(TILE_SIZE / 2, 0, TILE_SIZE , TILE_SIZE / 2);
        } else if (this.tileState.equals(TileState.START)) {
            this.setBackground(Color.GREEN);
        } else if (this.tileState.equals(TileState.END)) {
            this.setBackground(Color.RED);
        }

    }



    public void setTileState(TileState tileState) {
        this.tileState = tileState;

    }

    public void setVisited(boolean value) {
        this.visited = value;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public TileState getTileState() {
        return tileState;
    }
}
