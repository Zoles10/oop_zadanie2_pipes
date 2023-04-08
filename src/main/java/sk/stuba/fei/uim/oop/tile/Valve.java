package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public class Valve extends Tile {
    public Valve(int y, int x, int boardSize) {
        super(y, x, boardSize);
    }

    @Override
    public void switchCurrentTileState() {

    }

    @Override
    public void setCurrentTileStateRandom() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tileHalf = tileSize / 2;
        int tileQuarter = tileSize / 4;

        if(correctTileState.equals(TileState.VALVE_TOP)) {
            g.setColor(Color.GREEN);
            g.fillRect(tileQuarter, 0, tileHalf, tileSize*3/4);
        }
        if(correctTileState.equals(TileState.VALVE_BOTTOM)) {
            g.setColor(Color.GREEN);
            g.fillRect(tileQuarter, tileQuarter, tileHalf, tileSize);
        }
        if(correctTileState.equals(TileState.VALVE_RIGHT)) {
            g.setColor(Color.GREEN);
            g.fillRect(tileQuarter, tileQuarter, tileSize, tileHalf);
        }
        if(correctTileState.equals(TileState.VALVE_LEFT)) {
            g.setColor(Color.GREEN);
            g.fillRect(0, tileQuarter, tileSize*3/4, tileHalf);
        }


    }

}
