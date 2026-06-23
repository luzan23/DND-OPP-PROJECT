import java.util.List;

public class Warrior extends Player {
    private int abilityCoolDown;
    private int remainingCoolDown;

    public Warrior(String name, int healthPool, int attackPoints, int defencePoints, int abilityCoolDown) {
        super(name, healthPool, attackPoints, defencePoints);
        this.abilityCoolDown = abilityCoolDown;
        this.remainingCoolDown = 0;
    }

    public int getAbilityCoolDown() { return abilityCoolDown; }
    public int getRemainingCoolDown() { return remainingCoolDown; }

    @Override
    public void onTick() {

        if (remainingCoolDown > 0) remainingCoolDown--;
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        int extraHp = 5 * this.level;
        int extraAtk = 2 * this.level;
        int extraDef = this.level;
        this.healthPool += extraHp;
        this.healthAmount = this.healthPool;
        this.attackPoints += extraAtk;
        this.defencePoints += extraDef;
        this.remainingCoolDown = 0;
    }




    public void castSpecialAbility(List<Enemy> enemies) {
        if (remainingCoolDown > 0) {
            notify("Ability not ready. Remaining cooldown: " + remainingCoolDown);
            return;
        }
        int healAmount = 10 * this.defencePoints;
        this.healthAmount = Math.min(this.healthAmount + healAmount, this.healthPool);
        notify(this.name + " used Avenger's Shield, healing for " + healAmount + ".");
        remainingCoolDown = abilityCoolDown;

        // find enemies within range < 3
        java.util.List<Enemy> inRange = new java.util.ArrayList<>();
        for (Enemy e : enemies) {
            if (e.isAlive() && this.position.distance(e.getPosition()) < 3) {
                inRange.add(e);
            }
        }
        if (!inRange.isEmpty()) {
            Enemy target = inRange.get(random.nextInt(inRange.size()));
            int damage = (int)(this.healthPool * 0.1);
            boolean died = target.takeDamage(this, damage);
            if (died) {
                target.death(this);
            }
        }
    }

    @Override
    public String description() {
        return name + "\t\tHealth: " + healthAmount + "/" + healthPool +
                "\t\tAttack: " + attackPoints +
                "\t\tDefense: " + defencePoints +
                "\t\tLevel: " + level +
                "\t\tExperience: " + experience + "/" + (50 * level) +
                "\t\tCooldown: " + remainingCoolDown + "/" + abilityCoolDown;
    }


}
