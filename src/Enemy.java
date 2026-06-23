public abstract class Enemy extends Unit {

    protected int experienceValue;

    public Enemy(String name, int healthPool, int healthAmount, int attackPoints, int defencePoints) {
        super("e", this.position, name, healthPool, healthAmount, attackPoints, defencePoints);
    }

    public abstract void onEnemyTurn();
}
