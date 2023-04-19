package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;

public class PipeDirections {
    @Getter
    @Setter
    boolean top;
    @Getter
    @Setter
    boolean right;
    @Getter
    @Setter
    boolean bottom;
    @Getter
    @Setter
    boolean left;

    public PipeDirections() {
        this.top = false;
        this.right = false;
        this.bottom = false;
        this.left = false;
    }

    public void setDirections(boolean top, boolean right, boolean bottom, boolean left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public void setDirections(TileState state) {
        if (state.equals(TileState.I_PIPE_LEFT_RIGHT)) {
            this.setDirections(false, true, false, true);
        } else if (state.equals(TileState.I_PIPE_TOP_DOWN)) {
            this.setDirections(true, false, true, false);
        } else if (state.equals(TileState.L_PIPE_DOWN_LEFT)) {
            this.setDirections(false, false, true, true);
        } else if (state.equals(TileState.L_PIPE_DOWN_RIGHT)) {
            this.setDirections(false, true, true, false);
        } else if (state.equals(TileState.L_PIPE_TOP_RIGHT)) {
            this.setDirections(true, true, false, false);
        } else if (state.equals(TileState.L_PIPE_TOP_LEFT)) {
            this.setDirections(true, false, false, true);
        }
    }

}
