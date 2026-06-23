public class Monster extends Enemy {
    protected int visionRange;

    public Monster(char tile, String name, int healthPool, int attackPoints, int defencePoints,
                   int experienceValue, int visionRange) {
        super(tile, name, healthPool, attackPoints, defencePoints, experienceValue);
        this.visionRange = visionRange;
    }

    public void onEnemyTurn(GameBoard board, Player player) {
        Position myPos = this.getPosition();
        Position playerPos = player.getPosition();
        double dist = myPos.distance(playerPos);
        Position targetPos;

        if (dist < visionRange) {
            int dx = myPos.getX() - playerPos.getX();
            int dy = myPos.getY() - playerPos.getY();

            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    targetPos = new Position(myPos.getX() - 1, myPos.getY());
                } else {
                    targetPos = new Position(myPos.getX() + 1, myPos.getY());
                }
            } else {
                if (dy > 0) {
                    targetPos = new Position(myPos.getX(), myPos.getY() - 1);
                } else {
                    targetPos = new Position(myPos.getX(), myPos.getY() + 1);
                }
            }
        } else {
            int dir = random.nextInt(5);
            if (dir == 0) {
                targetPos = new Position(myPos.getX() - 1, myPos.getY());
            } else if (dir == 1) {
                targetPos = new Position(myPos.getX() + 1, myPos.getY());
            } else if (dir == 2) {
                targetPos = new Position(myPos.getX(), myPos.getY() - 1);
            } else if (dir == 3) {
                targetPos = new Position(myPos.getX(), myPos.getY() + 1);
            } else {
                targetPos = myPos;
            }
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
