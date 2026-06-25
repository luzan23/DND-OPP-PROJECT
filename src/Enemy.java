public abstract class Enemy extends Unit {
    protected int experienceValue;

    public Enemy(char tile, String name, int healthPool, int attackPoints, int defencePoints, int experienceValue) {
        super(tile, null, name, healthPool, attackPoints, defencePoints);
        this.experienceValue = experienceValue;
    }


    public abstract void onEnemyTurn(GameBoard board, Player player);


    @Override
    public void visit(Player p) {
        boolean playerDied = p.defend(this);
        if (playerDied) {
            p.title = 'X';
            notify(p.getName() + " was defeated!");
        }
    }

    // Enemy moves into Enemy cell — blocked
    @Override
    public void visit(Enemy e) { }

    @Override
    public void accept(OccupantVisitor v) {
        v.visit(this);
    }

    @Override
    public void death(Player killer) {
        notify(this.name + " died. " + killer.getName() + " gained " + this.experienceValue + " experience");
        killer.addExperience(this.experienceValue);
    }

    @Override
    public abstract String description();
}
