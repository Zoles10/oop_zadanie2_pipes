package sk.stuba.fei.uim.oop.tile;

import java.util.Arrays;

public class IPipe extends Tile {
    public IPipe(int y, int x, int boardSize) {
        super(y, x, boardSize);
    }

    @Override
    public void switchCurrentTileState(){
        TileState[] IStates = {
                TileState.I_PIPE_LEFT_RIGHT,
                TileState.I_PIPE_TOP_DOWN,};

        int currentIndex = Arrays.asList(IStates).indexOf(this.currentTileState);
        int nextIndex = (currentIndex + 1) % IStates.length;
        this.currentTileState = IStates[nextIndex];

    }

    @Override
    public void setCurrentTileStateRandom() {
        TileState[] IStates = {
                TileState.I_PIPE_LEFT_RIGHT,
                TileState.I_PIPE_TOP_DOWN,};


            int currentIndex = rand.nextInt(2);
            this.currentTileState = IStates[currentIndex];

    }

//    public void paintComponent(Graphics g) {
////        System.out.println("TILE ["+this.x+","+this.y+"]");
//        super.paintComponent(g);
//
//        if (this.currentTileState.equals(TileState.L_PIPE_DOWN_RIGHT)) {
//            g.fillRect(20, 20, tileSize/2, tileSize );
//            g.fillRect(20 , 20, tileSize , tileSize/2);
//        }else if (this.currentTileState.equals(TileState.L_PIPE_DOWN_LEFT)) {
//            g.fillRect(20, 20, tileSize/2, tileSize);
//            g.fillRect(0, 20, tileSize/2, tileSize/2);
//        }else if (this.currentTileState.equals(TileState.L_PIPE_TOP_RIGHT)) {
//            g.fillRect(20, 0, tileSize/2, tileSize/2);
//            g.fillRect(20 , 20, tileSize , tileSize/2);
//        }else if (this.currentTileState.equals(TileState.L_PIPE_TOP_LEFT)) {
//            g.fillRect(20, 0, tileSize/2, tileSize/2);
//            g.fillRect(0, 20, tileSize/2+20, tileSize/2);
//        }else if (this.currentTileState.equals(TileState.I_PIPE_TOP_DOWN)) {
//            g.fillRect(20, 0, tileSize/2, tileSize);
//        }else if (this.currentTileState.equals(TileState.I_PIPE_LEFT_RIGHT)) {
//            g.fillRect(0, 20, tileSize , tileSize / 2);
//        }else if (this.currentTileState.equals(TileState.START)) {
//            g.setColor(Color.GREEN);
//            g.fillRect(20, 20, tileSize/2 , tileSize / 2);
//        }else if (this.currentTileState.equals(TileState.END)) {
//            g.setColor(Color.RED);
//            g.fillRect(20, 20, tileSize/2 , tileSize / 2);
//        }
//
//        if(highlight){
//            this.setBackground(Color.LIGHT_GRAY);
//        }
//        else {
//            if(correctPosition){
//                this.setBackground(Color.GREEN);
//            }
//            else {
//                this.setBackground(Color.WHITE);
//            }
//        }
//    }
}
