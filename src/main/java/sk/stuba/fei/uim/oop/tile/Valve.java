package sk.stuba.fei.uim.oop.tile;

public class Valve extends Tile{
    public Valve(int y, int x, int boardSize) {
        super(y, x, boardSize);
    }

    @Override
    public void switchCurrentTileState() {

    }

    @Override
    public void setCurrentTileStateRandom() {

    }

//    @Override
//    public void paintComponent(Graphics g) {
////        System.out.println("TILE ["+this.x+","+this.y+"]");
//        super.paintComponent(g);
//
//        if (this.getCurrentTileState().equals(TileState.START)) {
//            g.setColor(Color.GREEN);
//            g.fillRect(20, 20, tileSize/2 , tileSize / 2);
//        }else if (this.getCurrentTileState().equals(TileState.END)) {
//            g.setColor(Color.RED);
//            g.fillRect(20, 20, tileSize/2 , tileSize / 2);
//        }

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
    }
//}
