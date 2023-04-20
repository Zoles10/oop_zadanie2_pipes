package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public class Valve extends Tile {
    public Valve(int y, int x, int boardSize) {
        super(y, x, boardSize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);

        int tileHalf = tileSize / 2;
        int tileQuarter = tileSize / 4;

        if (currentTileState.equals(TileState.VALVE_TOP)) {
             g.fillRect(tileQuarter, 0, tileHalf, tileHalf);
            if(correctPosition){
                g.setColor(Color.BLUE);
                g.fillRect(tileSize *3 / 8, 0,tileQuarter, tileHalf);
            }
        }
        if (currentTileState.equals(TileState.VALVE_BOTTOM)) {
            g.fillRect(tileQuarter, tileQuarter, tileHalf, tileSize);
            if(correctPosition){
                g.setColor(Color.BLUE);
                g.fillRect(tileSize *3 / 8, tileHalf,tileQuarter, tileHalf);
            }
        }
        if (currentTileState.equals(TileState.VALVE_RIGHT)) {
            g.fillRect(tileQuarter, tileQuarter, tileSize, tileHalf);
            if(correctPosition){
                g.setColor(Color.BLUE);
                g.fillRect(tileHalf, tileSize *3 / 8,tileSize, tileQuarter);
            }
        }
        if (currentTileState.equals(TileState.VALVE_LEFT)) {
            g.fillRect(0, tileQuarter, tileQuarter, tileHalf);
            if(correctPosition){
                g.setColor(Color.BLUE);
                g.fillRect(0, tileSize *3 / 8,tileSize *3 / 8, tileQuarter);
            }
        }

        Graphics2D g2d = (Graphics2D) g.create();

        int ovalSize = (tileSize / 10) * 8;
        int x = (tileSize - ovalSize) / 2;
        int y = (tileSize - ovalSize) / 2;

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(x, y, ovalSize, ovalSize);
        if (posX == 0) {
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.RED);
        }
        ovalSize = (tileSize / 10) * 6;
        x = (tileSize - ovalSize) / 2;
        y = (tileSize - ovalSize) / 2;
        g2d.setStroke(new BasicStroke(tileSize / 20));

        g2d.drawOval(x, y, ovalSize, ovalSize);
        g2d.drawOval(x, y, ovalSize, ovalSize);

        g2d.drawLine(x, y + ovalSize / 2, x + ovalSize, y + ovalSize / 2);
        g2d.drawLine(x + ovalSize / 2, y, x + ovalSize / 2, y + ovalSize);
    }

}
