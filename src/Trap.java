public class Trap extends Enemy {
    protected int visibilityTime;
    protected int invisibilityTime;
    protected int ticksCount;
    protected boolean visible;

    public Trap(char tile, String name, int healthPool, int attackPoints, int defencePoints,
                int experienceValue, int visibilityTime, int invisibilityTime) {
        super(tile, name, healthPool, attackPoints, defencePoints, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    @Override
    public String toString() {
        if (visible) {
            return String.valueOf(title);
        } else {
            return ".";
        }    }

    @Override
    public void onEnemyTurn(GameBoard board, Player player) {
        // update visibility
        visible = ticksCount < visibilityTime;
        if (ticksCount == visibilityTime + invisibilityTime) {
            ticksCount = 0;
        } else {
            ticksCount++;
        }
        // attack if player is adjacent
        if (this.position.distance(player.getPosition()) < 2) {
            boolean playerDied = player.defend(this);
            if (playerDied) {
                player.title = 'X';
                notify(player.getName() + " was defeated!");
            }
        }
    }

    @Override
    public String description() {
        return name + "\t\t\tHealth: " + healthAmount + "/" + healthPool +
                "\t\tAttack: " + attackPoints +
                "\t\tDefense: " + defencePoints +
                "\t\tExperience Value: " + experienceValue +
                "\t\tVisible: " + visible;
    }
}
