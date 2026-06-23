public class Mage extends Player implements HeroicUnit{

    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(int manaPool, int manaCost, int spellPower, int abilityRange, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints,int abilityCoolDown){
        super(name, healthPool, healthAmount, attackPoints, defensePoints);
        this.manaPool=manaPool;
        this.currentMana=manaPool/4;
        this.manaCost= manaCost;
        this.spellPower=spellPower;
        this.abilityRange=abilityRange;
    }
    @Override
    public void onTick() {
        this.currentMana=Math.min(manaPool, currentMana+this.level);
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        this.manaPool+=(10*this.level);
        this.currentMana=Math.min(currentMana+(manaPool/4), manaPool);
        this.spellPower+=(10*this.level);
    }

    @Override
    public void castAbility() {
        this.currentMana-=manaCost;
        int hits=0;
        //while(hits<this.hitsCount)
    }


    @Override
    public void death(Player p) {

    }
}
