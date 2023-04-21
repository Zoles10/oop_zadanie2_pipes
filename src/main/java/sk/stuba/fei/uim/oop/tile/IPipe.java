package sk.stuba.fei.uim.oop.tile;

import java.awt.*;
import java.util.Arrays;

public class IPipe extends Tile {
    private final TileState[] IStates;

    public IPipe(int y, int x, int boardSize) {
        super(y, x, boardSize);
        IStates = new TileState[]{
                TileState.I_PIPE_LEFT_RIGHT,
                TileState.I_PIPE_TOP_DOWN,};
    }

    @Override
    public void switchCurrentTileState() {
        int currentIndex = Arrays.asList(IStates).indexOf(this.currentTileState);
        int nextIndex = (currentIndex + 1) % IStates.length;
        this.currentTileState = IStates[nextIndex];
        this.directions.clear();
        setDirections(this.currentTileState);

    }

    @Override
    public void setCurrentTileStateRandom() {
        int currentIndex = rand.nextInt(2);
        this.currentTileState = IStates[currentIndex];
        this.directions.clear();
        setDirections(currentTileState);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.currentTileState.equals(TileState.I_PIPE_TOP_DOWN)) {
            paintLineTop(g);
            paintLineBottom(g);
            if (correctPosition) {
                paintWaterTop(g);
                paintWaterBottom(g);
            }
        } else if (this.currentTileState.equals(TileState.I_PIPE_LEFT_RIGHT)) {
            paintLineLeft(g);
            paintLineRight(g);
            if (correctPosition) {
                paintWaterLeft(g);
                paintWaterRight(g);
            }
        }
    }
}
