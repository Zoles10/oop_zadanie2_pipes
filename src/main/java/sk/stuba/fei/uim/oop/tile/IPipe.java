package sk.stuba.fei.uim.oop.tile;

import java.awt.*;
import java.util.Arrays;

public class IPipe extends Tile {
    TileState[] IStates;
    public IPipe(int y, int x, int boardSize) {
        super(y, x, boardSize);
        IStates = new TileState[]{
                TileState.I_PIPE_LEFT_RIGHT,
                TileState.I_PIPE_TOP_DOWN,};
    }

    @Override
    public void switchCurrentTileState(){
        int currentIndex = Arrays.asList(IStates).indexOf(this.currentTileState);
        int nextIndex = (currentIndex + 1) % IStates.length;
        this.currentTileState = IStates[nextIndex];

    }

    @Override
    public void setCurrentTileStateRandom() {
            int currentIndex = rand.nextInt(2);
            this.currentTileState = IStates[currentIndex];

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
         if (this.currentTileState.equals(TileState.I_PIPE_TOP_DOWN)) {
            g.fillRect(tileSize/4, 0, tileSize / 2, tileSize);
        } else if (this.currentTileState.equals(TileState.I_PIPE_LEFT_RIGHT)) {
            g.fillRect(0, tileSize/4, tileSize, tileSize / 2);
        }

    }

}
