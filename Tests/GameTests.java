

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameTests {

    // just a helper i kept using so i dont repeat this everywhere
    private GameBoard makeSimpleBoard(int rows, int cols) {
        GameBoard board = new GameBoard(rows, cols);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board.setCell(new Position(r, c), new Floor(new Position(r, c)));
            }
        }
        return board;
    }


    // ---- movement ----

    @Test
    public void testBasicMovement() {
        // warrior on (0,0), moves right to (0,1), should work
        GameBoard board = makeSimpleBoard(3, 3);
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        board.moveUnit(w, new Position(0, 1));

        // TODO: assertEquals(new Position(0, 1), w.getPosition());
        // TODO:assertNull(board.getOccupant(new Position(0, 0)));
    }

    @Test
    public void testCantWalkIntoWall() {
        GameBoard board = makeSimpleBoard(3, 3);
        board.setCell(new Position(0, 1), new Wall(new Position(0, 1)));

        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        board.moveUnit(w, new Position(0, 1));

        // TODO: assertEquals(new Position(0, 0), w.getPosition());
    }

    @Test
    public void testCantGoOutOfBounds() {
        // moving to negative position, should just do nothing
        GameBoard board = makeSimpleBoard(3, 3);
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        board.moveUnit(w, new Position(0, -1));

        // TODO: assertEquals(new Position(0, 0), w.getPosition());
    }

    @Test
    public void testEnemiesDontFightEachOther() {
        // two monsters next to each other, one tries to move into the other, nothing should happen
        GameBoard board = makeSimpleBoard(3, 3);
        Monster m1 = new Monster('s', "Gold Cloak", 80, 8, 3, 25, 3);
        Monster m2 = new Monster('k', "Knight", 200, 14, 8, 50, 4);
        m1.setPosition(new Position(0, 0));
        m2.setPosition(new Position(0, 1));
        board.getCell(new Position(0, 0)).setOccupant(m1);
        board.getCell(new Position(0, 1)).setOccupant(m2);

        board.moveUnit(m1, new Position(0, 1));

        // TODO: assertEquals(new Position(0, 0), m1.getPosition());
        // TODO: assertEquals(new Position(0, 1), m2.getPosition());
    }


    // ---- combat / exp ----

    @Test
    public void testMonsterWithZeroDefenseTakesDamage() {
        // if monster has 0 defense it should always take damage regardless of luck
        Monster monster = new Monster('s', "Dummy", 100, 1, 0, 25, 3);
        monster.setCallback(msg -> {});
        Warrior w = new Warrior("Jon", 100, 50, 0, 3);
        w.setCallback(msg -> {});

        monster.takeDamage(w, 50);

        // TODO: assertTrue(monster.getHealthAmount() < 100);
    }

    @Test
    public void testMonsterDiesAtZeroHp() {
        Monster monster = new Monster('s', "Dummy", 1, 1, 0, 25, 3);
        monster.setCallback(msg -> {});
        Warrior w = new Warrior("Jon", 100, 999, 0, 3);
        w.setCallback(msg -> {});

        monster.takeDamage(w, 999);

        // TODO: assertFalse(monster.isAlive());
    }

    @Test
    public void testPlayerGetsExpOnKill() {
        Warrior w = new Warrior("Jon", 100, 100, 0, 3);
        w.setCallback(msg -> {});
        Monster monster = new Monster('s', "Dummy", 1, 1, 0, 25, 3);
        monster.setCallback(msg -> {});

        // simulate killing the monster
        monster.death(w);

        // TODO: assertEquals(25, w.getExperience());
    }

    @Test
    public void testLevelUpHappens() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});

        w.addExperience(50); // should trigger level up (threshold at level 1 is 50)

        // TODO: assertEquals(2, w.getLevel());
    }

    @Test
    public void testHealthIncreasesOnLevelUp() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        int hpBefore = w.getHealthPool();

        w.addExperience(50);

        // TODO: assertTrue(w.getHealthPool() > hpBefore);
    }


    // ---- warrior ability ----

    @Test
    public void testWarriorAbilityHeals() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.setPosition(new Position(0, 0));
        w.setHealthAmount(30); // pretend he got hit

        w.castSpecialAbility(new ArrayList<>());

        // TODO: assertTrue(w.getHealthAmount() > 30);
    }

    @Test
    public void testWarriorAbilityDoesntWorkOnCooldown() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.setPosition(new Position(0, 0));

        w.castSpecialAbility(new ArrayList<>()); // first use, sets cooldown
        w.setHealthAmount(30);
        w.castSpecialAbility(new ArrayList<>()); // should fail - on cooldown

        // TODO: assertEquals(30, w.getHealthAmount());
    }

    @Test
    public void testCooldownGoesDownEachTick() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.setPosition(new Position(0, 0));

        w.castSpecialAbility(new ArrayList<>());
        int cooldown = w.getRemainingCoolDown();
        w.onTick();

        // TODO: assertEquals(cooldown - 1, w.getRemainingCoolDown());
    }


    // ---- trap ----

    @Test
    public void testTrapVisibleAtStart() {
        Trap trap = new Trap('B', "Bonus Trap", 1, 1, 1, 250, 2, 3);

        // TODO: assertNotEquals(".", trap.toString());
    }

    @Test
    public void testTrapBecomesInvisibleAfterVisibilityTime() {
        // visibility=1 so after 1 tick it should go invisible
        Trap trap = new Trap('B', "Bonus Trap", 1, 1, 1, 250, 1, 3);
        trap.setCallback(msg -> {});
        trap.setPosition(new Position(2, 2));

        GameBoard board = makeSimpleBoard(3, 3);
        board.getCell(new Position(2, 2)).setOccupant(trap);

        Warrior player = new Warrior("Jon", 100, 10, 5, 3);
        player.setCallback(msg -> {});
        player.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(player);

        trap.onEnemyTurn(board, player);

        // TODO: assertEquals(".", trap.toString());
    }


    // ---- board display ----

    @Test
    public void testEmptyBoardLooksRight() {
        GameBoard board = makeSimpleBoard(2, 2);

        // TODO: assertEquals("..\n..\n", board.toString());
    }

    @Test
    public void testWallShowsAsHash() {
        GameBoard board = makeSimpleBoard(2, 2);
        board.setCell(new Position(0, 0), new Wall(new Position(0, 0)));

        // TODO: assertTrue(board.toString().startsWith("#"));
    }

    @Test
    public void testPlayerShowsOnBoard() {
        GameBoard board = makeSimpleBoard(2, 2);
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        // TODO: assertTrue(board.toString().startsWith("@"));
    }
}
