import java.io.*;
import java.util.*;

public class Game {
    private Player player;
    private GameBoard board;
    private List<Enemy> enemies;
    private GameCallback callback;
    private String levelsDir;
    private int currentLevel;
    private int totalLevels;

    public Game(Player player, String levelsDir, GameCallback callback) throws IOException {
        this.player = player;
        this.levelsDir = levelsDir;
        this.callback = callback;
        this.currentLevel = 1;
        player.setCallback(callback);

        // ספירת רמות
        File dir = new File(levelsDir);
        int count = 0;
        while (new File(dir, "level" + (count + 1) + ".txt").exists()) count++;
        this.totalLevels = count;

        loadLevel(currentLevel);
    }

    private void loadLevel(int level) throws IOException {
        enemies = new ArrayList<>();
        String path = levelsDir + File.separator + "level" + level + ".txt";

        // קריאת הקובץ
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }
        br.close();

        // בניית הלוח
        int rows = lines.size();
        int cols = lines.get(0).length();
        board = new GameBoard(rows, cols);

        // מילוי הלוח
        for (int r = 0; r < rows; r++) {
            String currentLine = lines.get(r);
            for (int c = 0; c < currentLine.length(); c++) {
                char ch = currentLine.charAt(c);
                Position pos = new Position(r, c);
                if (ch == '#') {
                    board.setCell(pos, new Wall(pos));
                } else {
                    Floor floor = new Floor(pos);
                    board.setCell(pos, floor);
                    if (ch == '@') {
                        player.setPosition(pos);
                        floor.setOccupant(player);
                    } else if (ch != '.') {
                        Enemy e = EnemyFactory.chooseEnemy(ch, pos);
                        if (e != null) {
                            floor.setOccupant(e);
                            enemies.add(e);
                            e.setCallback(callback);
                        }
                    }
                }
            }
        }
    }

    public boolean isOver() {
        return !player.isAlive();
    }

    public boolean isWon() {
        return currentLevel > totalLevels;
    }

    public boolean tick(char action) throws IOException {
        if (!player.isAlive()) return false;

        // --- תור השחקן ---
        Position cur = player.getPosition();
        Position target;

        if (action == 'w') {
            target = new Position(cur.getX() - 1, cur.getY());
        } else if (action == 's') {
            target = new Position(cur.getX() + 1, cur.getY());
        } else if (action == 'a') {
            target = new Position(cur.getX(), cur.getY() - 1);
        } else if (action == 'd') {
            target = new Position(cur.getX(), cur.getY() + 1);
        } else {
            target = cur;
        }

        if (action == 'e') {
            player.castSpecialAbility(enemies);
        } else if (!target.equals(cur)) {
            board.moveUnit(player, target);
        }

        removeDeadEnemies();

        if (!player.isAlive()) return false;

        // בדיקת סיום רמה
        if (enemies.isEmpty()) {
            currentLevel++;
            if (currentLevel > totalLevels) {
                callback.onMessage("You won!");
                return false;
            }
            loadLevel(currentLevel);
            return true;
        }

        // --- תור האויבים ---
        player.onTick();
        List<Enemy> enemiesCopy = new ArrayList<>(enemies);
        for (Enemy e : enemiesCopy) {
            if (e.isAlive()) {
                e.onEnemyTurn(board, player);
            }
        }

        removeDeadEnemies();
        return player.isAlive();
    }

    private void removeDeadEnemies() {
        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy e = it.next();
            if (!e.isAlive()) {
                board.removeUnit(e);
                it.remove();
            }
        }
    }

    public String getBoardString() {
        return board.toString();
    }

    public String getPlayerStatus() {
        return player.description();
    }
}
