import java.util.List;

public class Hunter extends Player {
    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(String name, int healthPool, int attackPoints, int defencePoints, int range) {
        super(name, healthPool, attackPoints, defencePoints);
        this.range = range;
        this.arrowsCount = 10 * this.level;
        this.ticksCount = 0;
    }

    public int getArrowsCount() { return arrowsCount; }
    public int getRange() { return range; }

    @Override
    public void onTick() {
        if (ticksCount == 10) {
            arrowsCount += this.level;
            ticksCount = 0;
        } else {
            ticksCount++;
        }
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        this.arrowsCount += (10 * this.level);
        this.attackPoints += (2 * this.level);
        this.defencePoints += this.level;
    }

    @Override
    public void castAbility() {
        if (arrowsCount <= 0) {
            notify("No arrows left.");
            return;
        }
    }

    public void castAbility(List<Enemy> enemies) {
        if (arrowsCount <= 0) {
            notify("No arrows left.");
            return;
        }
        // find closest enemy within range
        Enemy closest = null;
        double minDist = Double.MAX_VALUE;
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                double dist = this.position.distance(e.getPosition());
                if (dist <= range && dist < minDist) {
                    minDist = dist;
                    closest = e;
                }
            }
        }
        if (closest == null) {
            notify("No enemies in range.");
            return;
        }
        arrowsCount--;
        notify(this.name + " shoots " + closest.getName() + "!");
        boolean died = closest.takeDamage(this, this.attackPoints);
        if (died) {
            closest.death(this);
        }
    }

    @Override
    public String description() {
        return name + "\t\tHealth: " + healthAmount + "/" + healthPool +
                "\t\tAttack: " + attackPoints +
                "\t\tDefense: " + defencePoints +
                "\t\tLevel: " + level +
                "\t\tExperience: " + experience + "/" + (50 * level) +
                "\t\tArrows: " + arrowsCount +
                "\t\tRange: " + range;
    }
}
