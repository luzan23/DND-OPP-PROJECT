import java.util.Random;

public abstract class Unit extends Occupant implements CellVisitor, OccupantVisitor {
    protected String name;
    protected int healthPool;
    protected int healthAmount;
    protected int attackPoints;
    protected int defencePoints;
    protected static final Random random = new Random();
    protected GameCallback callback;

    public Unit(char title, Position pos, String name, int healthPool, int attackPoints, int defencePoints) {
        super(title, pos);
        this.name = name;
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
        this.attackPoints = attackPoints;
        this.defencePoints = defencePoints;
    }

    public void setCallback(GameCallback cb) {
        this.callback = cb;
    }

    protected void notify(String msg) {
        if (callback != null) callback.onMessage(msg);
    }

    public String getName() { return name; }
    public int getHealthPool() { return healthPool; }
    public int getHealthAmount() { return healthAmount; }
    public int getAttackPoints() { return attackPoints; }
    public int getDefencePoints() { return defencePoints; }

    public boolean isAlive() {
        return healthAmount > 0;
    }

    public void setHealthAmount(int amount) {
        this.healthAmount = Math.max(0, Math.min(healthPool, amount));
    }

    public boolean defend(Unit attacker) {
        int attackRand = random.nextInt(attacker.attackPoints + 1);
        int defenseRand = random.nextInt(this.defencePoints + 1);
        int damage = Math.max(0, attackRand - defenseRand);
        notify(attacker.name + " attacked " + this.name + ".");
        notify(attacker.description());
        notify(this.description());
        notify(attacker.name + " rolled " + attackRand + " attack points.");
        notify(this.name + " rolled " + defenseRand + " defense points.");
        notify(attacker.name + " caused " + damage + " damage to " + this.name + ".");
        this.healthAmount = Math.max(0, this.healthAmount - damage);
        return this.healthAmount <= 0;
    }

    public boolean takeDamage(Unit attacker, int amount) {
        int defenseRoll = random.nextInt(this.defencePoints + 1);
        int damage = Math.max(0, amount - defenseRoll);
        notify(this.name + " rolled " + defenseRoll + " defense points.");
        notify(attacker.name + " hit " + this.name + " for " + damage + " ability damage.");
        this.healthAmount = Math.max(0, this.healthAmount - damage);
        return this.healthAmount <= 0;
    }

    public abstract void accept(OccupantVisitor v);
    public abstract void death(Player p);
    public abstract String description();

    // CellVisitor - Wall: blocked, do nothing
    @Override
    public void visit(Wall w) {
        // movement blocked
    }

    // CellVisitor - Floor: move or interact
    @Override
    public void visit(Floor f) {
        Occupant occ = f.getOccupant();
        if (occ == null) {
            this.setPosition(f.getPosition());
            f.setOccupant(this);
        } else {
            occ.accept(this);
        }
    }

    // OccupantVisitor defaults (overridden in subclasses)
    @Override
    public void visit(Player p) { }

    @Override
    public void visit(Enemy e) { }
}
