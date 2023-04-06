package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.tile.*;

import java.awt.*;
import javax.swing.*;
import  java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameBoard extends JPanel {
    private final int boardSize;
    private final  Tile[][] board;
    private final int start;
    private final int end;
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
                    this.board[i][j] = new Empty(i, j, boardSize);
                    this.board[i][j].setCurrentTileState(TileState.EMPTY);
                    this.board[i][j].setCorrectTileState(TileState.EMPTY);
            }
        }
        createPath(0,getStart(),getBoardSize()-1,getEnd());

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.add(this.board[i][j]);
            }
        }
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

        if(currentTile.getPosX()-1 >= 0){
            neighbors.add(board[currentTile.getPosY()][currentTile.getPosX()-1]);
        }
        if(currentTile.getPosX()+1 < boardSize){
            neighbors.add(board[currentTile.getPosY()][currentTile.getPosX()+1]);
        }
        if(currentTile.getPosY()-1 >= 0){
            neighbors.add(board[currentTile.getPosY()-1][currentTile.getPosX()]);
        }
        if(currentTile.getPosY()+1 < boardSize){
            neighbors.add(board[currentTile.getPosY()+1][currentTile.getPosX()]);
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
        Tile startTile = getTile(startX, startY);
        Tile endTile = getTile(endX, endY);

        startTile.setVisited(true);
        path.push(startTile);

        while (!path.empty()) {
            Tile currentTile = path.peek();
            if (currentTile == endTile) {
                path.push(currentTile);
                highlightPath();
                startTile.setCorrectTileState(TileState.VALVE);
                startTile.setCurrentTileState(TileState.VALVE);
                endTile.setCorrectTileState(TileState.VALVE);
                endTile.setCurrentTileState(TileState.VALVE);
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

            if (currentTile.getPosX() == prevTile.getPosX()) {
                if (currentTile.getPosY() > prevTile.getPosY()) {
                    topCount++;
                } else {
                    bottomCount++;
                }
            } else {
                if (currentTile.getPosX() > prevTile.getPosX()) {
                    leftCount++;
                } else {
                    rightCount++;
                }
            }

            if (currentTile.getPosX() == nextTile.getPosX()) {
                if (currentTile.getPosY() > nextTile.getPosY()) {
                    topCount++;
                } else {
                    bottomCount++;
                }
            } else {
                if (currentTile.getPosX() > nextTile.getPosX()) {
                    leftCount++;
                } else {
                    rightCount++;
                }

            }

            if (leftCount == 1 && bottomCount == 1) {
                currentTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(),boardSize);
                this.board[currentTile.getPosY()][currentTile.getPosX()] = currentTile;
                path.set(i,currentTile);
                currentTile.setCorrectTileState(TileState.L_PIPE_DOWN_LEFT);
            } else if (leftCount == 1 && topCount == 1) {
                currentTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(),boardSize);
                this.board[currentTile.getPosY()][currentTile.getPosX()] = currentTile;
                path.set(i,currentTile);
                currentTile.setCorrectTileState(TileState.L_PIPE_TOP_LEFT);
            } else if (rightCount == 1 && bottomCount == 1) {
                currentTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(),boardSize);
                this.board[currentTile.getPosY()][currentTile.getPosX()] = currentTile;
                path.set(i,currentTile);
                currentTile.setCorrectTileState(TileState.L_PIPE_DOWN_RIGHT);
            } else if (rightCount == 1 && topCount == 1) {
                currentTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(),boardSize);
                this.board[currentTile.getPosY()][currentTile.getPosX()] = currentTile;
                path.set(i,currentTile);
                currentTile.setCorrectTileState(TileState.L_PIPE_TOP_RIGHT);
            } else if (leftCount + rightCount == 0 && (topCount > 0 || bottomCount > 0)) {
                currentTile = new IPipe(currentTile.getPosY(), currentTile.getPosX(),boardSize);
                this.board[currentTile.getPosY()][currentTile.getPosX()] = currentTile;
                path.set(i,currentTile);
                currentTile.setCorrectTileState(TileState.I_PIPE_TOP_DOWN);
            } else if (topCount + bottomCount == 0 && (leftCount > 0 || rightCount > 0)) {
                currentTile = new IPipe(currentTile.getPosY(), currentTile.getPosX(),boardSize);
                this.board[currentTile.getPosY()][currentTile.getPosX()] = currentTile;
                path.set(i,currentTile);
                currentTile.setCorrectTileState(TileState.I_PIPE_LEFT_RIGHT);
            } else {
                currentTile = new Valve(currentTile.getPosY(), currentTile.getPosX(),boardSize);
                this.board[currentTile.getPosY()][currentTile.getPosX()] = currentTile;
                path.set(i,currentTile);
                currentTile.setCorrectTileState(TileState.VALVE);
                currentTile.setCurrentTileState(TileState.VALVE);
            }
            currentTile.setCurrentTileStateRandom();
            repaint();
        }
        this.board[end][boardSize-1] = new Valve(end, boardSize-1,boardSize);
        path.set(path.size()-1,this.board[end][boardSize-1]);
        this.board[end][boardSize-1].setCorrectTileState(TileState.VALVE);
        this.board[end][boardSize-1].setCurrentTileState(TileState.VALVE);
    }

    public boolean checkWin(){
        for(Tile tile : path){
            if(tile.getCorrectTileState().equals(TileState.VALVE) ){
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
