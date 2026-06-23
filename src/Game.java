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

        // propagate callback to player and all units
        player.setCallback(callback);

        // count levels
        File dir = new File(levelsDir);
        int count = 0;
        while (new File(dir, "level" + (count + 1) + ".txt").exists()) count++;
        this.totalLevels = count;

        loadLevel(currentLevel);
    }

    private void loadLevel(int level) throws IOException {
        enemies = new ArrayList<>();
        Player[] slot = {player};
        String path = levelsDir + File.separator + "level" + level + ".txt";
        board = new GameBoard(path, enemies, slot);
        // set callback for all enemies
        for (Enemy e : enemies) {
            e.setCallback(callback);
        }
    }

    public boolean isOver() {
        return !player.isAlive();
    }

    public boolean isWon() {
        return currentLevel > totalLevels;
    }

    /** Process one player action. Returns true if game continues. */
    public boolean tick(char action) throws IOException {
        if (!player.isAlive()) return false;

        // --- Player turn ---
        Position cur = player.getPosition();
        Position target = switch (action) {
            case 'w' -> new Position(cur.getX() - 1, cur.getY());
            case 's' -> new Position(cur.getX() + 1, cur.getY());
            case 'a' -> new Position(cur.getX(), cur.getY() - 1);
            case 'd' -> new Position(cur.getX(), cur.getY() + 1);
            case 'e' -> null; // ability
            case 'q' -> cur;  // do nothing
            default  -> cur;
        };

        if (action == 'e') {
            castPlayerAbility();
        } else if (target != null && !target.equals(cur)) {
            board.moveUnit(player, target);
        }

        // remove dead enemies
        removeDeadEnemies();

        if (!player.isAlive()) return false;

        // check level complete
        if (enemies.isEmpty()) {
            currentLevel++;
            if (currentLevel > totalLevels) {
                callback.onMessage("You won!");
                return false;
            }
            loadLevel(currentLevel);
            return true;
        }

        // --- Enemies turn ---
        player.onTick();
        for (Enemy e : new ArrayList<>(enemies)) {
            if (e.isAlive()) {
                e.onEnemyTurn(board, player);
            }
        }

        removeDeadEnemies();
        return player.isAlive();
    }

    private void castPlayerAbility() {
        if (player instanceof Warrior w) {
            w.castAbility(enemies);
        } else if (player instanceof Mage m) {
            m.castAbility(enemies);
        } else if (player instanceof Rogue r) {
            r.castAbility(enemies);
        } else if (player instanceof Hunter h) {
            h.castAbility(enemies);
        } else {
            player.castAbility();
        }
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
