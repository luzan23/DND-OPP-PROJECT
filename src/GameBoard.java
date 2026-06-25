import java.io.*;
import java.util.*;

public class GameBoard {
    private Cell[][] board;
    private int rows;
    private int cols;

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new Cell[rows][cols];
    }


    public void setCell(Position p, Cell c) {
        board[p.getX()][p.getY()] = c;
    }

    public Cell getCell(Position p) {
        return board[p.getX()][p.getY()];
    }

    public void moveUnit(Unit u, Position targetPos) {
        int r = targetPos.getX();
        int c = targetPos.getY();
        if (r < 0 || r >= rows || c < 0 || c >= cols) return;
        Position oldPos = u.getPosition();
        Cell targetCell = board[r][c];
        targetCell.accept(u);
        if (!u.getPosition().equals(oldPos)) {
            board[oldPos.getX()][oldPos.getY()].setOccupant(null);
        }
    }

    public void removeUnit(Unit u) {
        Position p = u.getPosition();
        if (p != null) {
            board[p.getX()][p.getY()].setOccupant(null);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result = result + board[r][c].toString();
            }
            result = result + "\n";
        }
        return result;
    }
}
