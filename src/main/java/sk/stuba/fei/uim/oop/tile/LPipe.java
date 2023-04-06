package sk.stuba.fei.uim.oop.tile;

import java.util.Arrays;

public class LPipe extends Tile {
    public LPipe(int y, int x, int boardSize) {
        super(y, x, boardSize);
    }

    @Override
    public void switchCurrentTileState(){
        TileState[] LStates = {
                TileState.L_PIPE_TOP_LEFT,
                TileState.L_PIPE_TOP_RIGHT,
                TileState.L_PIPE_DOWN_LEFT,
                TileState.L_PIPE_DOWN_RIGHT,};

            int currentIndex = Arrays.asList(LStates).indexOf(this.currentTileState);
            int nextIndex = (currentIndex + 1) % LStates.length;
            this.currentTileState = LStates[nextIndex];

    }
    @Override
    public void setCurrentTileStateRandom() {
        TileState[] LStates = {
                TileState.L_PIPE_TOP_LEFT,
                TileState.L_PIPE_TOP_RIGHT,
                TileState.L_PIPE_DOWN_LEFT,
                TileState.L_PIPE_DOWN_RIGHT,};

        int currentIndex = rand.nextInt(4);
        this.currentTileState = LStates[currentIndex];


    }

}
