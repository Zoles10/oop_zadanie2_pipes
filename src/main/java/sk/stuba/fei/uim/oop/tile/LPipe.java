package sk.stuba.fei.uim.oop.tile;

import java.awt.*;
import java.util.Arrays;

public class LPipe extends Tile {
    TileState[] LStates;
    public LPipe(int y, int x, int boardSize) {
        super(y, x, boardSize);
        this.LStates = new TileState[]{
                TileState.L_PIPE_TOP_LEFT,
                TileState.L_PIPE_TOP_RIGHT,
                TileState.L_PIPE_DOWN_LEFT,
                TileState.L_PIPE_DOWN_RIGHT,};
    }

    @Override
    public void switchCurrentTileState(){
            int currentIndex = Arrays.asList(LStates).indexOf(this.currentTileState);
            int nextIndex = (currentIndex + 1) % LStates.length;
            this.currentTileState = LStates[nextIndex];

    }
    @Override
    public void setCurrentTileStateRandom() {
        int currentIndex = rand.nextInt(4);
        this.currentTileState = LStates[currentIndex];
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.currentTileState.equals(TileState.L_PIPE_DOWN_RIGHT)) {
            g.fillRect(tileSize/4, tileSize/4, tileSize / 2, tileSize);
            g.fillRect(tileSize/4, tileSize/4, tileSize, tileSize / 2);
        } else if (this.currentTileState.equals(TileState.L_PIPE_DOWN_LEFT)) {
            g.fillRect(tileSize/4, tileSize/4, tileSize / 2, tileSize);
            g.fillRect(0, tileSize/4, tileSize / 2, tileSize / 2);
        } else if (this.currentTileState.equals(TileState.L_PIPE_TOP_RIGHT)) {
            g.fillRect(tileSize/4, 0, tileSize / 2, tileSize / 2);
            g.fillRect(tileSize/4, tileSize/4, tileSize, tileSize / 2);
        } else if (this.currentTileState.equals(TileState.L_PIPE_TOP_LEFT)) {
            g.fillRect(tileSize/4, 0, tileSize / 2, tileSize / 2);
            g.fillRect(0, tileSize/4, tileSize / 2 + tileSize/4, tileSize / 2);
        }
    }
}
