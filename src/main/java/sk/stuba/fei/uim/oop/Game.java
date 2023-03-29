package sk.stuba.fei.uim.oop;

public class Game {
    public static final int TILE_SIZE = 50;
    public static final int BOARD_TILE_SIZE = 10;

    Game(){
        int boardSize = TILE_SIZE*BOARD_TILE_SIZE;
        System.out.println(boardSize);
        new GameBoard(boardSize);

    }
}
