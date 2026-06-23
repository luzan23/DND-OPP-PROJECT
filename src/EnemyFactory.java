public class EnemyFactory {

    public static Enemy chooseEnemy(char tile, Position pos) {
        Enemy e = null;

        if (tile == 's') {
            e = new Monster('s', "Gold Cloak", 80, 8, 3, 25, 3);
        } else if (tile == 'k') {
            e = new Monster('k', "Knight", 200, 14, 8, 50, 4);
        } else if (tile == 'q') {
            e = new Monster('q', "Queen's Guard", 400, 20, 15, 100, 5);
        } else if (tile == 'z') {
            e = new Monster('z', "Wright", 600, 30, 15, 100, 3);
        } else if (tile == 'b') {
            e = new Monster('b', "Bear", 1000, 75, 30, 250, 4);
        } else if (tile == 'g') {
            e = new Monster('g', "Giant", 1500, 100, 40, 500, 5);
        } else if (tile == 'w') {
            e = new Monster('w', "White Walker", 2000, 150, 50, 1000, 6);
        } else if (tile == 'M') {
            e = new Boss('M', "The Mountain", 1000, 60, 25, 500, 6, 5);
        } else if (tile == 'C') {
            e = new Boss('C', "Queen Cersei", 100, 10, 10, 1000, 1, 8);
        } else if (tile == 'K') {
            e = new Boss('K', "Night's King", 5000, 300, 150, 5000, 8, 3);
        } else if (tile == 'B') {
            e = new Trap('B', "Bonus Trap", 1, 1, 1, 250, 1, 5);
        } else if (tile == 'Q') {
            e = new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 7);
        } else if (tile == 'D') {
            e = new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10);
        }

        if (e != null) {
            e.setPosition(pos);
        }
        return e;
    }
}