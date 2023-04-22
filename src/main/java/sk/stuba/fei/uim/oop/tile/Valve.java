package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public class Valve extends Tile {
    public Valve(int y, int x, int boardSize) {
        super(y, x, boardSize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);

        if (currentTileState.equals(TileState.VALVE_TOP)) {
            paintLineTop(g);
            if (correctPosition) {
                paintWaterTop(g);
            }
        }
        if (currentTileState.equals(TileState.VALVE_BOTTOM)) {
            paintLineBottom(g);
            if (correctPosition) {
                paintWaterBottom(g);
            }
        }
        if (currentTileState.equals(TileState.VALVE_RIGHT)) {
            paintLineRight(g);
            if (correctPosition) {
                paintWaterRight(g);
            }
        }
        if (currentTileState.equals(TileState.VALVE_LEFT)) {
            paintLineLeft(g);
            if (correctPosition) {
                paintWaterLeft(g);
            }
        }

        Graphics2D g2d = (Graphics2D) g.create();
        paintValve(g2d);
    }

    private void paintValve(Graphics2D g2d) {
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
