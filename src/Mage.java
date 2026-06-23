import java.util.List;
import java.util.ArrayList;

public class Mage extends Player {
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(String name, int healthPool, int attackPoints, int defencePoints,
                int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, healthPool, attackPoints, defencePoints);
        this.manaPool = manaPool;
        this.currentMana = manaPool / 4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    public int getManaPool() { return manaPool; }
    public int getCurrentMana() { return currentMana; }
    public int getSpellPower() { return spellPower; }

    @Override
    public void onTick() {
        currentMana = Math.min(manaPool, currentMana + this.level);
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        this.manaPool += (25 * this.level);
        this.currentMana = Math.min(currentMana + (manaPool / 4), manaPool);
        this.spellPower += (10 * this.level);
    }



    public void castSpecialAbility(List<Enemy> enemies) {
        if (currentMana < manaCost) {
            notify("Not enough mana.");
            return;
        }
        currentMana -= manaCost;
        notify(this.name + " cast Blizzard!");

        List<Enemy> inRange = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.isAlive() && this.position.distance(e.getPosition()) < abilityRange) {
                inRange.add(e);
            }
        }

        int hits = 0;
        while (hits < hitsCount && !inRange.isEmpty()) {
            Enemy target = inRange.get(random.nextInt(inRange.size()));
            boolean died = target.takeDamage(this, spellPower);
            if (died) {
                target.death(this);
                inRange.remove(target);
            }
            hits++;
        }
    }

    @Override
    public String description() {
        return name + "\t\tHealth: " + healthAmount + "/" + healthPool +
                "\t\tAttack: " + attackPoints +
                "\t\tDefense: " + defencePoints +
                "\t\tLevel: " + level +
                "\t\tExperience: " + experience + "/" + (50 * level) +
                "\t\tMana: " + currentMana + "/" + manaPool +
                "\t\tSpell Power: " + spellPower;
    }
}
