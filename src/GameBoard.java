import java.io.*;
import java.util.*;

public class GameBoard {
    private Cell[][] board;
    private int rows;
    private int cols;

    public GameBoard(String filePath, List<Enemy> enemies, Player[] playerSlot) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        rows = lines.size();
        cols = lines.get(0).length();
        board = new Cell[rows][cols];

        for (int r = 0; r < rows; r++) {
            String line = lines.get(r);
            for (int c = 0; c < line.length(); c++) {
                char ch = line.charAt(c);
                Position pos = new Position(r, c);
                if (ch == '#') {
                    board[r][c] = new Wall(pos);
                } else {
                    Floor floor = new Floor(pos);
                    board[r][c] = floor;
                    if (ch == '@') {
                        playerSlot[0].setPosition(pos);
                        floor.setOccupant(playerSlot[0]);
                    } else if (ch != '.') {
                        Enemy e = EnemyFactory.create(ch, pos);
                        if (e != null) {
                            floor.setOccupant(e);
                            enemies.add(e);
                        }
                    }
                }
            }
        }
    }

    public Cell getCell(Position p) {
        return board[p.getX()][p.getY()];
    }

    public Occupant getOccupant(Position p) {
        Cell cell = board[p.getX()][p.getY()];
        return cell.getOccupant();
    }

    public void setOccupant(Position p, Occupant o) {
        board[p.getX()][p.getY()].setOccupant(o);
    }

    public void moveUnit(Unit u, Position targetPos) {
        int r = targetPos.getX();
        int c = targetPos.getY();
        if (r < 0 || r >= rows || c < 0 || c >= cols) return;

        Position oldPos = u.getPosition();
        Cell targetCell = board[r][c];
        targetCell.accept(u);

        // if position changed (unit moved successfully), clear old cell
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
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sb.append(board[r][c].toString());
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}
