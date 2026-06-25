import java.util.List;

public class    Rogue extends Player {
    private int cost;
    private int currentEnergy;

    public Rogue(String name, int healthPool, int attackPoints, int defencePoints, int cost) {
        super(name, healthPool, attackPoints, defencePoints);
        this.cost = cost;
        this.currentEnergy = 100;
    }

    public int getCurrentEnergy() { return currentEnergy; }

    @Override
    public void onTick() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        this.currentEnergy = 100;
        this.attackPoints += (3 * this.level);
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) {
        if (currentEnergy < cost) {
            notify("Not enough energy.");
            return;
        }
        currentEnergy -= cost;
    }

    public void castAbility(List<Enemy> enemies) {
        if (currentEnergy < cost) {
            notify("Not enough energy.");
            return;
        }
        currentEnergy -= cost;
        notify(this.name + " used Fan of Knives!");

        for (Enemy e : new java.util.ArrayList<>(enemies)) {
            if (e.isAlive() && this.position.distance(e.getPosition()) < 2) {
                boolean died = e.takeDamage(this, this.attackPoints);
                if (died) {
                    e.death(this);
                }
            }
        }
    }

    @Override
    public String description() {
        return name + "     Health: " + healthAmount + "/" + healthPool +
                "   Attack: " + attackPoints +
                "   Defense: " + defencePoints +
                "   Level: " + level +
                "   Experience: " + experience + "/" + (50 * level) +
                "   Energy: " + currentEnergy + "/100";
    }
}
