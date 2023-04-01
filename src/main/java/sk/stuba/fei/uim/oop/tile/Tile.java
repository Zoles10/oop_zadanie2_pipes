package sk.stuba.fei.uim.oop.tile;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Tile extends JPanel {
    private final int x;
    private final int y;
    private TileState correctTileState;
    private TileState currentTileState;
    private boolean visited;
    public int tileSize;
    private PipeShape pipeShape;
    Random rand;
    private boolean highlight;
    private boolean correctPosition;

    public Tile(int y, int x, int boardSize) {
        this.x = x;
        this.y = y;

        this.rand = new Random();

        this.highlight = false;

        this.correctPosition = false;

        tileSize=800/boardSize;

        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tileSize, tileSize);
    }

    public void setTileState(TileState tileState) {
        this.correctTileState = tileState;
        this.currentTileState = tileState;

    }

    public void setPipeShape(PipeShape pipeShape) {
        this.pipeShape = pipeShape;
    }

    public void setVisited(boolean value) {
        this.visited = value;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public int tileX() {
        return this.x;
    }

    public int tileY() {
        return this.y;
    }

    public TileState getCorrectTileState() {
        return correctTileState;
    }

    public void setHighlight(boolean value){
        this.highlight=value;
    }

    public void paintComponent(Graphics g) {
//        System.out.println("TILE ["+this.x+","+this.y+"]");
        super.paintComponent(g);

        if (this.currentTileState.equals(TileState.L_PIPE_DOWN_RIGHT)) {
            g.fillRect(20, 20, tileSize/2, tileSize );
            g.fillRect(20 , 20, tileSize , tileSize/2);
        }else if (this.currentTileState.equals(TileState.L_PIPE_DOWN_LEFT)) {
            g.fillRect(20, 20, tileSize/2, tileSize);
            g.fillRect(0, 20, tileSize/2, tileSize/2);
        }else if (this.currentTileState.equals(TileState.L_PIPE_TOP_RIGHT)) {
            g.fillRect(20, 0, tileSize/2, tileSize/2);
            g.fillRect(20 , 20, tileSize , tileSize/2);
        }else if (this.currentTileState.equals(TileState.L_PIPE_TOP_LEFT)) {
            g.fillRect(20, 0, tileSize/2, tileSize/2);
            g.fillRect(0, 20, tileSize/2+20, tileSize/2);
        }else if (this.currentTileState.equals(TileState.I_PIPE_TOP_DOWN)) {
            g.fillRect(20, 0, tileSize/2, tileSize);
        }else if (this.currentTileState.equals(TileState.I_PIPE_LEFT_RIGHT)) {
            g.fillRect(0, 20, tileSize , tileSize / 2);

         }else if (this.currentTileState.equals(TileState.START)) {
            g.setColor(Color.GREEN);
            g.fillRect(20, 20, tileSize/2 , tileSize / 2);
        }else if (this.currentTileState.equals(TileState.END)) {
            g.setColor(Color.RED);
            g.fillRect(20, 20, tileSize/2 , tileSize / 2);
        }

        if(highlight){
            this.setBackground(Color.LIGHT_GRAY);
        }
        else {
            if(correctPosition){
                this.setBackground(Color.GREEN);
            }
            else {
                this.setBackground(Color.WHITE);
            }
        }
    }

    public void switchCurrentTileState(){
//        this.highlight = true;
        if(pipeShape==null || correctTileState.equals(TileState.START)||correctTileState.equals(TileState.END)){
            return;
        }
        TileState[] LStates = {
                TileState.L_PIPE_TOP_LEFT,
                TileState.L_PIPE_TOP_RIGHT,
                TileState.L_PIPE_DOWN_LEFT,
                TileState.L_PIPE_DOWN_RIGHT,};
        TileState[] IStates = {
                TileState.I_PIPE_LEFT_RIGHT,
                TileState.I_PIPE_TOP_DOWN,};

        if(this.pipeShape.equals(PipeShape.L_SHAPE)) {
            int currentIndex = Arrays.asList(LStates).indexOf(this.currentTileState);
            int nextIndex = (currentIndex + 1) % LStates.length;
            this.currentTileState = LStates[nextIndex];
        }
        else if(this.pipeShape.equals(PipeShape.I_SHAPE)){
            int currentIndex = Arrays.asList(IStates).indexOf(this.currentTileState);
            int nextIndex = (currentIndex + 1) % IStates.length;
            this.currentTileState = IStates[nextIndex];
        }

    }

    public boolean checkCorrectShape(){
        return correctTileState == currentTileState;
    }

    public void setCurrentTileStateRandom() {
        if (pipeShape == null) {
            return;
        }
        TileState[] LStates = {
                TileState.L_PIPE_TOP_LEFT,
                TileState.L_PIPE_TOP_RIGHT,
                TileState.L_PIPE_DOWN_LEFT,
                TileState.L_PIPE_DOWN_RIGHT,};
        TileState[] IStates = {
                TileState.I_PIPE_LEFT_RIGHT,
                TileState.I_PIPE_TOP_DOWN,};

        if (this.pipeShape.equals(PipeShape.L_SHAPE)) {
            int currentIndex = rand.nextInt(4);
            this.currentTileState = LStates[currentIndex];
        } else if (this.pipeShape.equals(PipeShape.I_SHAPE)) {
            int currentIndex = rand.nextInt(2);
            this.currentTileState = IStates[currentIndex];
        }

    }

    public void setCorrectPosition(boolean correctPosition) {
        this.correctPosition = correctPosition;
    }
}