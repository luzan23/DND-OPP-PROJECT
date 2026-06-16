public class Boss extends Enemy implements HeroicUnit{
    protected int visionRange;
    protected int abilityFreq;
    protected int combatTicks;
    public Boss(int vR, int aF, String name, int healthPool, int healthAmount, int attackPoints, int defencePoints) {
        super(name, healthPool, healthAmount, attackPoints, defencePoints);
        this.visionRange=vR;
        this.abilityFreq=aF;
        this.combatTicks=0;
    }

    @Override
    public void accept(OccupantVisitor v) {

    }

    @Override
    public void death(Player p) {

    }

    @Override
    public void castAbility() {

    }

    @Override
    public void onEnemyTurn() {

    }
}
