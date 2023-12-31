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
        int x = currentTile.getPosX();
        int y = currentTile.getPosY();
        int[][] offsets = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] offset : offsets) {
            int neighborX = x + offset[1];
            int neighborY = y + offset[0];

            if (neighborX >= 0 && neighborX < boardSize && neighborY >= 0 && neighborY < boardSize) {
                Tile neighbor = board[neighborY][neighborX];
                if (neighbor != null) {
                    neighbors.add(neighbor);
                }
            }
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
                if (neighbor != null && !neighbor.isVisited()) {
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
            Tile[] neighbourTiles = {prevTile, nextTile};
            for (Tile neighbourTile : neighbourTiles) {
                if (currentTile.getPosX() == neighbourTile.getPosX()) {
                    if (currentTile.getPosY() > neighbourTile.getPosY()) {
                        topCount++;
                    } else {
                        bottomCount++;
                    }
                } else {
                    if (currentTile.getPosX() > neighbourTile.getPosX()) {
                        leftCount++;
                    } else {
                        rightCount++;
                    }
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
        } else if (leftCount == 1 && topCount == 1) {
            newTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
        } else if (rightCount == 1 && bottomCount == 1) {
            newTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
        } else if (rightCount == 1 && topCount == 1) {
            newTile = new LPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
        } else if (leftCount + rightCount == 0 && (topCount > 0 || bottomCount > 0)) {
            newTile = new IPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
        } else if (topCount + bottomCount == 0 && (leftCount > 0 || rightCount > 0)) {
            newTile = new IPipe(currentTile.getPosY(), currentTile.getPosX(), boardSize);
        } else {
            return;
        }
        newTile.setCurrentTileStateRandom();
        newTile.setDirections(newTile.getCurrentTileState());
        this.board[newTile.getPosY()][newTile.getPosX()] = newTile;
        this.path.set(this.path.indexOf(currentTile), newTile);
        repaint();
    }

    public boolean checkWin() {
        Tile currentTile = path.get(0);
        Tile previousTile = null;
        boolean neighbourFlag;
        while (true) {
            if (currentTile.equals(path.get(path.size() - 1))) {
                currentTile.setCorrectPosition(true);
                return true;
            }
            neighbourFlag = false;
            List<Tile> neighbours = getNeighbors(currentTile);
            for (Tile neighbour : neighbours) {
                for (Directions direction : neighbour.getDirections()) {
                    if (direction.equals(Directions.LEFT) && currentTile.getDirections().contains(Directions.RIGHT) && !neighbour.equals(previousTile) && neighbour.getPosX() == currentTile.getPosX() + 1) {
                        neighbourFlag = true;
                    } else if (direction.equals(Directions.RIGHT) && currentTile.getDirections().contains(Directions.LEFT) && !neighbour.equals(previousTile) && neighbour.getPosX() == currentTile.getPosX() - 1) {
                        neighbourFlag = true;
                    } else if (direction.equals(Directions.TOP) && currentTile.getDirections().contains(Directions.BOTTOM) && !neighbour.equals(previousTile) && neighbour.getPosY() == currentTile.getPosY() + 1) {
                        neighbourFlag = true;
                    } else if (direction.equals(Directions.BOTTOM) && currentTile.getDirections().contains(Directions.TOP) && !neighbour.equals(previousTile) && neighbour.getPosY() == currentTile.getPosY() - 1) {
                        neighbourFlag = true;
                    }
                }
                if (neighbourFlag) {
                    currentTile.setCorrectPosition(true);
                    previousTile = currentTile;
                    currentTile = neighbour;
                    repaint();
                    break;
                }
            }
            if (!neighbourFlag) {
                currentTile.setCorrectPosition(true);
                return false;
            }
        }
    }

    public void resetCorrectPosition() {
        for (Tile tile : path) {
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
            valve.setCurrentTileState(TileState.VALVE_BOTTOM);
            valve.setDirections(TileState.VALVE_BOTTOM);
        }
        if (valve.getPosX() == nextPipe.getPosX() && valve.getPosY() > nextPipe.getPosY()) {
            valve.setCurrentTileState(TileState.VALVE_TOP);
            valve.setDirections(TileState.VALVE_TOP);
        }
        if (valve.getPosX() < nextPipe.getPosX() && valve.getPosY() == nextPipe.getPosY()) {
            valve.setCurrentTileState(TileState.VALVE_RIGHT);
            valve.setDirections(TileState.VALVE_RIGHT);
        }
        if (valve.getPosX() > nextPipe.getPosX() && valve.getPosY() == nextPipe.getPosY()) {
            valve.setCurrentTileState(TileState.VALVE_LEFT);
            valve.setDirections(TileState.VALVE_LEFT);
        }
    }
}
