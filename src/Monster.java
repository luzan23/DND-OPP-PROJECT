public class Monster extends Enemy {
    protected int visionRange;

    public Monster(char tile, String name, int healthPool, int attackPoints, int defencePoints,
                   int experienceValue, int visionRange) {
        super(tile, name, healthPool, attackPoints, defencePoints, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public void onEnemyTurn(GameBoard board, Player player) {
        Position myPos = this.getPosition();
        Position playerPos = player.getPosition();
        double dist = myPos.distance(playerPos);

        Position targetPos;
        if (dist < visionRange) {
            // chase player
            int dx = myPos.getX() - playerPos.getX();
            int dy = myPos.getY() - playerPos.getY();
            if (Math.abs(dx) > Math.abs(dy)) {
                targetPos = dx > 0
                        ? new Position(myPos.getX() - 1, myPos.getY())
                        : new Position(myPos.getX() + 1, myPos.getY());
            } else {
                targetPos = dy > 0
                        ? new Position(myPos.getX(), myPos.getY() - 1)
                        : new Position(myPos.getX(), myPos.getY() + 1);
            }
        } else {
            // random movement
            int dir = random.nextInt(5);
            targetPos = switch (dir) {
                case 0 -> new Position(myPos.getX() - 1, myPos.getY()); // left
                case 1 -> new Position(myPos.getX() + 1, myPos.getY()); // right
                case 2 -> new Position(myPos.getX(), myPos.getY() - 1); // up
                case 3 -> new Position(myPos.getX(), myPos.getY() + 1); // down
                default -> myPos; // stay
            };
        }

        if (!targetPos.equals(myPos)) {
            board.moveUnit(this, targetPos);
        }
    }

    @Override
    public String description() {
        return name + "\t\t\tHealth: " + healthAmount + "/" + healthPool +
                "\t\tAttack: " + attackPoints +
                "\t\tDefense: " + defencePoints +
                "\t\tExperience Value: " + experienceValue +
                "\t\tVision Range: " + visionRange;
    }
}
