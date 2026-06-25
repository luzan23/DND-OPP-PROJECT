public class EnemyFactory {

    public static Enemy chooseEnemy(char title, Position pos) {
        Enemy e = switch (title) {
            case 's' -> new Monster('s', "Gold Cloak", 80, 8, 3, 25, 3);
            case 'k' -> new Monster('k', "Knight", 200, 14, 8, 50, 4);
            case 'q' -> new Monster('q', "Queen's Guard", 400, 20, 15, 100, 5);
            case 'z' -> new Monster('z', "Wright", 600, 30, 15, 100, 3);
            case 'b' -> new Monster('b', "Bear", 1000, 75, 30, 250, 4);
            case 'g' -> new Monster('g', "Giant", 1500, 100, 40, 500, 5);
            case 'w' -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6);
            case 'M' -> new Boss('M', "The Mountain", 1000, 60, 25, 500, 6, 5);
            case 'C' -> new Boss('C', "Queen Cersei", 100, 10, 10, 1000, 1, 8);
            case 'K' -> new Boss('K', "Night's King", 5000, 300, 150, 5000, 8, 3);
            case 'B' -> new Trap('B', "Bonus Trap", 1, 1, 1, 250, 1, 5);
            case 'Q' -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 7);
            case 'D' -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10);
            default -> null;
        };
        if (e != null) {
            e.setPosition(pos);
        }
        return e;
    }
}