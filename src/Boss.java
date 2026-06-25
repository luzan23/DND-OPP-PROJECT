import java.util.List;

public class Boss extends Monster implements HeroicUnit {
    private int abilityFrequency;
    private int combatTicks;

    public Boss(char tile, String name, int healthPool, int attackPoints, int defencePoints,
                int experienceValue, int visionRange, int abilityFrequency) {
        super(tile, name, healthPool, attackPoints, defencePoints, experienceValue, visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    private void shoebodybop(Player player) {
        notify(this.name + " used Shoebodybop!");
        boolean playerDied = player.defend(this);
        if (playerDied) {
            player.title = 'X';
            notify(player.getName() + " was defeated!");
        }
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) { }

    @Override
    public void onEnemyTurn(GameBoard board, Player player) {
        Position myPos = this.getPosition();
        Position playerPos = player.getPosition();
        double dist = myPos.distance(playerPos);

        if (dist < visionRange) {
            if (combatTicks == abilityFrequency) {
                combatTicks = 0;
                shoebodybop(player);
            } else {
                combatTicks++;
            }
            int dx = myPos.getX() - playerPos.getX();
            int dy = myPos.getY() - playerPos.getY();
            Position targetPos;
            if (Math.abs(dx) > Math.abs(dy)) {
                targetPos = dx > 0
                        ? new Position(myPos.getX() - 1, myPos.getY())
                        : new Position(myPos.getX() + 1, myPos.getY());
            } else {
                targetPos = dy > 0
                        ? new Position(myPos.getX(), myPos.getY() - 1)
                        : new Position(myPos.getX(), myPos.getY() + 1);
            }
            if (!targetPos.equals(myPos)) {
                board.moveUnit(this, targetPos);
            }
        } else {
            combatTicks = 0;
            int dir = random.nextInt(5);
            Position targetPos = switch (dir) {
                case 0 -> new Position(myPos.getX() - 1, myPos.getY());
                case 1 -> new Position(myPos.getX() + 1, myPos.getY());
                case 2 -> new Position(myPos.getX(), myPos.getY() - 1);
                case 3 -> new Position(myPos.getX(), myPos.getY() + 1);
                default -> myPos;
            };
            if (!targetPos.equals(myPos)) {
                board.moveUnit(this, targetPos);
            }
        }
    }
}
