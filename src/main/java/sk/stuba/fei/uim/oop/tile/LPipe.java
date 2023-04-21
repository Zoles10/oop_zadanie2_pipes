package sk.stuba.fei.uim.oop.tile;

import java.awt.*;
import java.util.Arrays;

public class LPipe extends Tile {
    private final TileState[] LStates;
    public LPipe(int y, int x, int boardSize) {
        super(y, x, boardSize);
        this.LStates = new TileState[]{
                TileState.L_PIPE_TOP_LEFT,
                TileState.L_PIPE_TOP_RIGHT,
                TileState.L_PIPE_DOWN_RIGHT,
                TileState.L_PIPE_DOWN_LEFT,};
    }

    @Override
    public void switchCurrentTileState() {
        int currentIndex = Arrays.asList(LStates).indexOf(this.currentTileState);
        int nextIndex = (currentIndex + 1) % LStates.length;
        this.currentTileState = LStates[nextIndex];
        this.directions.clear();
        setDirections(this.currentTileState);
    }

    @Override
    public void setCurrentTileStateRandom() {
        int currentIndex = rand.nextInt(4);
        this.currentTileState = LStates[currentIndex];
        this.directions.clear();
        setDirections(currentTileState);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.currentTileState.equals(TileState.L_PIPE_DOWN_RIGHT)) {
            paintLineBottom(g);
            paintLineRight(g);
            if (correctPosition) {
                paintWaterBottom(g);
                paintWaterRight(g);
            }
        } else if (this.currentTileState.equals(TileState.L_PIPE_DOWN_LEFT)) {
            paintLineLeft(g);
            paintLineBottom(g);
            if (correctPosition) {
                paintWaterBottom(g);
                paintWaterLeft(g);
            }
        } else if (this.currentTileState.equals(TileState.L_PIPE_TOP_RIGHT)) {
            paintLineRight(g);
            paintLineTop(g);
            if (correctPosition) {
                paintWaterTop(g);
                paintWaterRight(g);
            }
        } else if (this.currentTileState.equals(TileState.L_PIPE_TOP_LEFT)) {
            paintLineLeft(g);
            paintLineTop(g);
            if (correctPosition) {
                paintWaterTop(g);
                paintWaterLeft(g);
            }
        }
    }
}
