import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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



    @Test
    public void testBasicMovement() {
        // warrior on (0,0), moves right to (0,1), should work
        GameBoard board = makeSimpleBoard(3, 3);
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        board.moveUnit(w, new Position(0, 1));

        assertEquals(new Position(0, 1), w.getPosition());
        assertNull(board.getCell(new Position(0, 0)).getOccupant());
    }

    @Test
    public void testCantWalkIntoWall() {
        GameBoard board = makeSimpleBoard(3, 3);
        board.setCell(new Position(0, 1), new Wall(new Position(0, 1)));

        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        board.moveUnit(w, new Position(0, 1));

        assertEquals(new Position(0, 0), w.getPosition());
    }

    @Test
    public void testCantGoOutOfBounds() {
        // moving to negative position, should just do nothing
        GameBoard board = makeSimpleBoard(3, 3);
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        board.moveUnit(w, new Position(0, -1));

        assertEquals(new Position(0, 0), w.getPosition());
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

        assertEquals(new Position(0, 0), m1.getPosition());
        assertEquals(new Position(0, 1), m2.getPosition());
    }

    @Test
    public void testMoveUpdatesOldCell() {
        // after moving, the old cell should be empty
        GameBoard board = makeSimpleBoard(3, 3);
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(1, 1));
        board.getCell(new Position(1, 1)).setOccupant(w);

        board.moveUnit(w, new Position(1, 2));

        assertNull(board.getCell(new Position(1, 1)).getOccupant());
        assertEquals(w, board.getCell(new Position(1, 2)).getOccupant());
    }



    @Test
    public void testMonsterWithZeroDefenseTakesDamage() {
        // if monster has 0 defense it should always take damage regardless of luck
        Monster monster = new Monster('s', "Dummy", 100, 1, 0, 25, 3);
        monster.setCallback(msg -> {});
        Warrior w = new Warrior("Jon", 100, 50, 0, 3);
        w.setCallback(msg -> {});

        monster.takeDamage(w, 50);

        assertTrue(monster.getHealthAmount() < 100);
    }

    @Test
    public void testMonsterDiesAtZeroHp() {
        Monster monster = new Monster('s', "Dummy", 1, 1, 0, 25, 3);
        monster.setCallback(msg -> {});
        Warrior w = new Warrior("Jon", 100, 999, 0, 3);
        w.setCallback(msg -> {});

        monster.takeDamage(w, 999);

        assertFalse(monster.isAlive());
    }

    @Test
    public void testPlayerGetsExpOnKill() {
        Warrior w = new Warrior("Jon", 100, 100, 0, 3);
        w.setCallback(msg -> {});
        Monster monster = new Monster('s', "Dummy", 1, 1, 0, 25, 3);
        monster.setCallback(msg -> {});

        monster.death(w);

        assertEquals(25, w.getExperience());
    }

    @Test
    public void testLevelUpHappens() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});

        w.addExperience(50);

        assertEquals(2, w.getLevel());
    }

    @Test
    public void testHealthIncreasesOnLevelUp() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        int hpBefore = w.getHealthPool();

        w.addExperience(50);

        assertTrue(w.getHealthPool() > hpBefore);
    }

    @Test
    public void testExpResetsAfterLevelUp() {
        // after leveling up experience should not stay at 50, it resets
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.addExperience(50);
        assertEquals(0, w.getExperience());
    }

    @Test
    public void testMultipleLevelUps() {
        // giving a lot of exp at once should level up multiple times
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.addExperience(200);
        assertTrue(w.getLevel() > 2);
    }

    @Test
    public void testHealthCantGoBelowZero() {
        Monster monster = new Monster('s', "Dummy", 50, 1, 0, 25, 3);
        monster.setCallback(msg -> {});
        Warrior w = new Warrior("Jon", 100, 999, 0, 3);
        w.setCallback(msg -> {});
        monster.takeDamage(w, 99999);
        assertEquals(0, monster.getHealthAmount());
    }


    @Test
    public void testWarriorAbilityHeals() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.setPosition(new Position(0, 0));
        w.setHealthAmount(30);
        w.castSpecialAbility(new ArrayList<>());
        assertTrue(w.getHealthAmount() > 30);
    }

    @Test
    public void testWarriorAbilityDoesntWorkOnCooldown() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.setPosition(new Position(0, 0));
        w.castSpecialAbility(new ArrayList<>());
        w.setHealthAmount(30);
        w.castSpecialAbility(new ArrayList<>());
        assertEquals(30, w.getHealthAmount());
    }

    @Test
    public void testCooldownGoesDownEachTick() {
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.setPosition(new Position(0, 0));
        w.castSpecialAbility(new ArrayList<>());
        int cooldown = w.getRemainingCoolDown();
        w.onTick();
        assertEquals(cooldown - 1, w.getRemainingCoolDown());
    }

    @Test
    public void testWarriorCantHealAboveMax() {
        // warrior starts full hp, ability shouldnt go over healthPool
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setCallback(msg -> {});
        w.setPosition(new Position(0, 0));
        w.castSpecialAbility(new ArrayList<>());
        assertTrue(w.getHealthAmount() <= w.getHealthPool());
    }



    @Test
    public void testMageStartsWithMana() {
        Mage mage = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        assertEquals(75, mage.getCurrentMana());
    }

    @Test
    public void testMageCantCastWithoutMana() {
        // manaCost is higher than starting mana so cast should fail
        Mage mage = new Mage("Melisandre", 100, 5, 1, 100, 999, 15, 5, 6);
        mage.setCallback(msg -> {});
        mage.setPosition(new Position(0, 0));

        int manaBefore = mage.getCurrentMana();
        mage.castSpecialAbility(new ArrayList<>());

        assertEquals(manaBefore, mage.getCurrentMana()); // mana didnt change
    }

    @Test
    public void testMageRechargesManaOnTick() {
        Mage mage = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        mage.setCallback(msg -> {});
        int manaBefore = mage.getCurrentMana();

        mage.onTick();

        assertTrue(mage.getCurrentMana() > manaBefore);
    }



    @Test
    public void testRogueStartsFullEnergy() {
        Rogue r = new Rogue("Arya", 150, 40, 2, 20);

        assertEquals(100, r.getCurrentEnergy());
    }

    @Test
    public void testRogueEnergyRechargesOnTick() {
        Rogue r = new Rogue("Arya", 150, 40, 2, 20);
        r.setCallback(msg -> {});
        r.castSpecialAbility(new ArrayList<>()); // spend some energy
        int energyAfterCast = r.getCurrentEnergy();

        r.onTick();

        assertTrue(r.getCurrentEnergy() > energyAfterCast);
    }

    @Test
    public void testRogueCantCastWithoutEnergy() {
        // cost is 999 so should always fail
        Rogue r = new Rogue("Arya", 150, 40, 2, 999);
        r.setCallback(msg -> {});

        int energyBefore = r.getCurrentEnergy();
        r.castSpecialAbility(new ArrayList<>());

        assertEquals(energyBefore, r.getCurrentEnergy());
    }



    @Test
    public void testHunterStartsWithArrows() {
        Hunter h = new Hunter("Ygritte", 220, 30, 2, 6);

        assertTrue(h.getArrowsCount() > 0);
    }

    @Test
    public void testHunterShootingUsesArrow() {
        GameBoard board = makeSimpleBoard(5, 5);
        Hunter h = new Hunter("Ygritte", 220, 30, 2, 6);
        h.setCallback(msg -> {});
        h.setPosition(new Position(0, 0));

        Monster monster = new Monster('s', "Dummy", 100, 1, 0, 25, 10);
        monster.setCallback(msg -> {});
        monster.setPosition(new Position(0, 2));

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(monster);

        int arrowsBefore = h.getArrowsCount();
        h.castSpecialAbility(enemies);

        assertEquals(arrowsBefore - 1, h.getArrowsCount());
    }



    @Test
    public void testTrapVisibleAtStart() {
        Trap trap = new Trap('B', "Bonus Trap", 1, 1, 1, 250, 2, 3);

        assertNotEquals(".", trap.toString());
    }

    @Test
    public void testTrapBecomesInvisibleAfterVisibilityTime() {
        // visibility=1 so after 2 ticks it should go invisible
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
        trap.onEnemyTurn(board, player);

        assertEquals(".", trap.toString());
    }

    @Test
    public void testTrapDoesntMoveEver() {
        // trap should always stay in the same position
        Trap trap = new Trap('B', "Bonus Trap", 1, 1, 1, 250, 2, 3);
        trap.setCallback(msg -> {});
        trap.setPosition(new Position(2, 2));

        GameBoard board = makeSimpleBoard(5, 5);
        board.getCell(new Position(2, 2)).setOccupant(trap);

        Warrior player = new Warrior("Jon", 100, 10, 5, 3);
        player.setCallback(msg -> {});
        player.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(player);

        trap.onEnemyTurn(board, player);
        trap.onEnemyTurn(board, player);
        trap.onEnemyTurn(board, player);

        assertEquals(new Position(2, 2), trap.getPosition());
    }



    @Test
    public void testEmptyBoardLooksRight() {
        GameBoard board = makeSimpleBoard(2, 2);

        assertEquals("..\n..\n", board.toString());
    }

    @Test
    public void testWallShowsAsHash() {
        GameBoard board = makeSimpleBoard(2, 2);
        board.setCell(new Position(0, 0), new Wall(new Position(0, 0)));

        assertTrue(board.toString().startsWith("#"));
    }

    @Test
    public void testPlayerShowsOnBoard() {
        GameBoard board = makeSimpleBoard(2, 2);
        Warrior w = new Warrior("Jon", 100, 10, 5, 3);
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        assertTrue(board.toString().startsWith("@"));
    }

    @Test
    public void testDeadPlayerShowsX() {
        // when player dies their tile changes to X
        GameBoard board = makeSimpleBoard(3, 3);
        Warrior w = new Warrior("Jon", 1, 10, 0, 3);
        w.setCallback(msg -> {});
        w.setPosition(new Position(0, 0));
        board.getCell(new Position(0, 0)).setOccupant(w);

        Monster monster = new Monster('s', "Dummy", 100, 999, 0, 25, 3);
        monster.setCallback(msg -> {});
        monster.setPosition(new Position(0, 1));
        board.getCell(new Position(0, 1)).setOccupant(monster);

        // monster attacks player directly
        boolean died = w.defend(monster);
        if (died) w.title = 'X';

        assertTrue(board.toString().startsWith("X"));
    }
}