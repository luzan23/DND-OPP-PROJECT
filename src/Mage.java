public class Mage extends Player implements HeroicUnit{

    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(int manaPool, int manaCost, int spellPower, int abilityRange){
        this.manaPool=manaPool;
        this.currentMana=manaPool/4;
        this.manaCost= manaCost;
        this.spellPower=spellPower;
        this.abilityRange=abilityRange;
    }
    @Override
    public void onTick() {

    }

    @Override
    protected void levelUp() {

    }

    @Override
    public void accept(Occupant o) {

    }

    public void castSpecialAbility() {

    }

    @Override
    public void castAbility() {

    }

    @Override
    public void death() {

    }
}
