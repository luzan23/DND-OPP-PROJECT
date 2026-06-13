public class Mage extends Player {
    private int manaPool;
    private int manaAmount;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(String name, int healthPool, int attackPoints, int defencepoints, int manaPool, int manaCost, int spellPower, int abilityRange, int hitsCount) {
        super(name, healthPool, attackPoints, defencepoints);
        this.manaPool = manaPool;
        this.manaAmount = manaPool / 4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.abilityRange = abilityRange;
        this.hitsCount = hitsCount;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.manaPool += 25 * level;
        this.manaAmount = Math.min(manaAmount + manaPool / 4, manaPool);
        this.spellPower += 10 * level;
    }

    @Override
    public void castAbility() {
        if (manaAmount < manaCost) {
            // TODO: לשלוח הודעה שאין מספיק מאנה
            return;
        }
        manaAmount -= manaCost;
        // TODO: לוגיקת פגיעות קסם אקראיות באויבים בטווח
    }

    @Override
    public void onTick() {
        manaAmount = Math.min(manaAmount + level, manaPool);
    }

    @Override
    public void accept(OccupantVisitor v) {

    }
}