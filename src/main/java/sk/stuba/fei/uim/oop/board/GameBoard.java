package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.tile.PipeShape;
import sk.stuba.fei.uim.oop.tile.Tile;
import sk.stuba.fei.uim.oop.tile.TileState;

import java.awt.*;
import javax.swing.*;
import  java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameBoard extends JPanel {
    private final int boardSize;
    private  Tile[][] board;
    int start;
    int end;
    private final Random rand;
    Stack<Tile> path;

    public GameBoard(int boardSize) {
        this.path = new Stack<>();
        this.boardSize=boardSize;
        this.rand = new Random();
        this.start = rand.nextInt(boardSize);
        this.end = rand.nextInt(boardSize);
        this.board = new Tile[boardSize][boardSize];
        initializeBoard(boardSize);
    }

    private void initializeBoard(int boardSize) {
        this.setLayout(new GridLayout(boardSize, boardSize));

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                    this.board[i][j] = new Tile(i, j, boardSize);
                    this.board[i][j].setTileState(TileState.EMPTY);
                    this.add(this.board[i][j]);
            }
        }
        createPath(0,getStart(),getBoardSize()-1,getEnd());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800   , 800);
    }

    public Tile getTile(int x, int y){
        return board[y][x];
    }

    public int getBoardSize(){
        return this.boardSize;
    }

    public List<Tile> getNeighbors(Tile currentTile){

        java.util.List<Tile> neighbors = new ArrayList<>();

        if(currentTile.tileX()-1 >= 0){
            neighbors.add(board[currentTile.tileY()][currentTile.tileX()-1]);
        }
        if(currentTile.tileX()+1 < boardSize){
            neighbors.add(board[currentTile.tileY()][currentTile.tileX()+1]);
        }
        if(currentTile.tileY()-1 >= 0){
            neighbors.add(board[currentTile.tileY()-1][currentTile.tileX()]);
        }
        if(currentTile.tileY()+1 < boardSize){
            neighbors.add(board[currentTile.tileY()+1][currentTile.tileX()]);
        }
        return neighbors;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void createPath(int startX, int startY, int endX, int endY) {
        System.out.println("here");
        Tile startTile = getTile(startX, startY);
        Tile endTile = getTile(endX, endY);

        startTile.setVisited(true);
        path.push(startTile);

        while (!path.empty()) {
            Tile currentTile = path.peek();
            if (currentTile == endTile) {
                path.push(currentTile);
                highlightPath();
                startTile.setTileState(TileState.START);
                endTile.setTileState(TileState.END);
                return;
            }

            List<Tile> neighbors = getNeighbors(currentTile);
            List<Tile> unvisitedNeighbors = new ArrayList<>();
            for (Tile neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    unvisitedNeighbors.add(neighbor);
                }
            }

            if (!unvisitedNeighbors.isEmpty()) {
                Tile nextTile = unvisitedNeighbors.get(new Random().nextInt(unvisitedNeighbors.size()));
                nextTile.setVisited(true);
                path.push(nextTile);
            } else {
                path.pop();
            }
        }
    }

    private void highlightPath() {
        for (int i = 1; i < path.size() - 1; i++) {
            Tile currentTile = path.get(i);
            Tile prevTile = path.get(i - 1);
            Tile nextTile = path.get(i + 1);

            int leftCount = 0;
            int rightCount = 0;
            int topCount = 0;
            int bottomCount = 0;

            if (currentTile.tileX() == prevTile.tileX()) {
                if (currentTile.tileY() > prevTile.tileY()) {
                    topCount++;
                } else {
                    bottomCount++;
                }
            } else {
                if (currentTile.tileX() > prevTile.tileX()) {
                    leftCount++;
                } else {
                    rightCount++;
                }
            }

            if (currentTile.tileX() == nextTile.tileX()) {
                if (currentTile.tileY() > nextTile.tileY()) {
                    topCount++;
                } else {
                    bottomCount++;
                }
            } else {
                if (currentTile.tileX() > nextTile.tileX()) {
                    leftCount++;
                } else {
                    rightCount++;
                }

            }

            if (leftCount == 1 && bottomCount == 1) {
                currentTile.setTileState(TileState.L_PIPE_DOWN_LEFT);
                currentTile.setPipeShape(PipeShape.L_SHAPE);
            } else if (leftCount == 1 && topCount == 1) {
                currentTile.setTileState(TileState.L_PIPE_TOP_LEFT);
                currentTile.setPipeShape(PipeShape.L_SHAPE);
            } else if (rightCount == 1 && bottomCount == 1) {
                currentTile.setTileState(TileState.L_PIPE_DOWN_RIGHT);
                currentTile.setPipeShape(PipeShape.L_SHAPE);
            } else if (rightCount == 1 && topCount == 1) {
                currentTile.setTileState(TileState.L_PIPE_TOP_RIGHT);
                currentTile.setPipeShape(PipeShape.L_SHAPE);
            } else if (leftCount + rightCount == 0 && (topCount > 0 || bottomCount > 0)) {
                currentTile.setTileState(TileState.I_PIPE_TOP_DOWN);
                currentTile.setPipeShape(PipeShape.I_SHAPE);
            } else if (topCount + bottomCount == 0 && (leftCount > 0 || rightCount > 0)) {
                currentTile.setTileState(TileState.I_PIPE_LEFT_RIGHT);
                currentTile.setPipeShape(PipeShape.I_SHAPE);
            } else {
                currentTile.setTileState(TileState.EMPTY);
            }
            currentTile.setCurrentTileStateRandom();
            repaint();
        }
    }

    public boolean checkWin(){
        for(Tile tile : path){
            if(tile.getCorrectTileState().equals(TileState.START) || tile.getCorrectTileState().equals(TileState.END)){
                continue;
            }
            if(!tile.checkCorrectShape()){
                this.repaint();
                return false;
            }
            tile.setCorrectPosition(true);
        }
        this.repaint();

        return true;
    }

}
