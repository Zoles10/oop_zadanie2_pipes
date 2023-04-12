package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import sk.stuba.fei.uim.oop.tile.*;

import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameBoard extends JPanel {
    @Getter
    private final int boardSize;
    private final Tile[][] board;
    @Getter
    private final int start;
    @Getter
    private final int end;
    private final Random rand;
    private final Stack<Tile> path;

    public GameBoard(int boardSize) {
        this.path = new Stack<>();
        this.boardSize = boardSize;
        this.rand = new Random();
        this.start = rand.nextInt(boardSize);
        this.end = rand.nextInt(boardSize);
        this.board = new Tile[boardSize][boardSize];
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.setBackground(Color.RED);
        initializeBoard(boardSize);
    }

    private void initializeBoard(int boardSize) {
        this.setLayout(new GridLayout(boardSize, boardSize));
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = new Tile(i, j, boardSize);
                this.board[i][j].setCurrentTileState(TileState.EMPTY);
                this.board[i][j].setCorrectTileState(TileState.EMPTY);
            }
        }
        createPath(0, getStart(), getBoardSize() - 1, getEnd());
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.add(this.board[i][j]);
            }
        }
    }

    private List<Tile> getNeighbors(Tile currentTile) {
        java.util.List<Tile> neighbors = new ArrayList<>();
        if (currentTile.getPosX() - 1 >= 0) {
            neighbors.add(board[currentTile.getPosY()][currentTile.getPosX() - 1]);
        }
        if (currentTile.getPosX() + 1 < boardSize) {
            neighbors.add(board[currentTile.getPosY()][currentTile.getPosX() + 1]);
        }
        if (currentTile.getPosY() - 1 >= 0) {
            neighbors.add(board[currentTile.getPosY() - 1][currentTile.getPosX()]);
        }
        if (currentTile.getPosY() + 1 < boardSize) {
            neighbors.add(board[currentTile.getPosY() + 1][currentTile.getPosX()]);
        }
        return neighbors;
    }

    private void createPath(int startX, int startY, int endX, int endY) {
        Valve startTile = new Valve(startY, startX, boardSize);
        Valve endTile = new Valve(endY, endX, boardSize);
        board[startY][startX] = startTile;
        board[endY][endX] = endTile;
        startTile.setVisited(true);
        path.push(startTile);
        while (!path.empty()) {
            Tile currentTile = path.peek();
            if (currentTile == endTile) {
                path.push(currentTile);
                highlightPath();
                setValves(startTile, endTile);
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
            if (currentTile.getPosX() == nextTile.getPosX() ) {
                if (currentTile.getPosY() > nextTile.getPosY() ) {
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
            if (currentTile instanceof Valve) {
                continue;
            }
            setNewTile(currentTile, leftCount, rightCount, topCount, bottomCount);
        }

    }

    private void setNewTile(Tile currentTile, int leftCount, int rightCount, int topCount, int bottomCount) {
        Tile newTile;
        if (leftCount == 1 && bottomCount == 1) {
            newTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
            newTile.setCorrectTileState(TileState.L_PIPE_DOWN_LEFT);
        } else if (leftCount == 1 && topCount == 1) {
            newTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
            newTile.setCorrectTileState(TileState.L_PIPE_TOP_LEFT);
        } else if (rightCount == 1 && bottomCount == 1) {
            newTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
            newTile.setCorrectTileState(TileState.L_PIPE_DOWN_RIGHT);
        } else if (rightCount == 1 && topCount == 1) {
            newTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
            newTile.setCorrectTileState(TileState.L_PIPE_TOP_RIGHT);
        } else if (leftCount + rightCount == 0 && (topCount > 0 || bottomCount > 0)) {
            newTile = new IPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
            newTile.setCorrectTileState(TileState.I_PIPE_TOP_DOWN);
        } else if (topCount + bottomCount == 0 && (leftCount > 0 || rightCount > 0)) {
            newTile = new IPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
            newTile.setCorrectTileState(TileState.I_PIPE_LEFT_RIGHT);
        } else {
            return;
        }
        newTile.setCurrentTileStateRandom();
        this.board[newTile.getPosY()][newTile.getPosX()] = newTile;
        this.path.set(this.path.indexOf(currentTile), newTile);
        repaint();
    }


    public boolean checkWin() {
        for (Tile tile : path) {
            if (tile instanceof Valve) {
                continue;
            }
            if (!tile.checkCorrectShape()) {
                this.repaint();
                return false;
            }
            tile.setCorrectPosition(true);
            this.repaint();
        }
        return true;
    }

    public void resetCorrectPosition() {
        for (Tile tile : path) {
            if (tile instanceof Valve) {
                continue;
            }
            if (tile.isCorrectPosition()) {
                tile.setCorrectPosition(false);
            }
        }
        this.repaint();
    }

    private void setValves(Tile startTile, Tile endTile) {
        Tile firstPipe = path.get(path.indexOf(startTile) + 1);
        Tile lastPipe = path.get(path.indexOf(endTile) - 1);
        setValve(startTile, firstPipe);
        setValve(endTile, lastPipe);
    }

    private void setValve(Tile valve, Tile nextPipe) {
        if (valve.getPosX() == nextPipe.getPosX() && valve.getPosY() < nextPipe.getPosY()) {
            valve.setCorrectTileState(TileState.VALVE_BOTTOM);
            valve.setCurrentTileState(TileState.VALVE_BOTTOM);
        }
        if (valve.getPosX() == nextPipe.getPosX() && valve.getPosY() > nextPipe.getPosY()) {
            valve.setCorrectTileState(TileState.VALVE_TOP);
            valve.setCurrentTileState(TileState.VALVE_TOP);
        }
        if (valve.getPosX() < nextPipe.getPosX() && valve.getPosY() == nextPipe.getPosY()) {
            valve.setCorrectTileState(TileState.VALVE_RIGHT);
            valve.setCurrentTileState(TileState.VALVE_RIGHT);
        }
        if (valve.getPosX() > nextPipe.getPosX() && valve.getPosY() == nextPipe.getPosY()) {
            valve.setCorrectTileState(TileState.VALVE_LEFT);
            valve.setCurrentTileState(TileState.VALVE_LEFT);
        }
    }

}
