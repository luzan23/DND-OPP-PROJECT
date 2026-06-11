public class Warrior extends Player implements HeroicUnit{

    private int abilityCoolDown;
    private int remainingCoolDown;

    public Warrior(int abilityCoolDown){
        this.abilityCoolDown=abilityCoolDown;
        this.remainingCoolDown=0;
    }

    @Override
    public void onTick() {
        this.remainingCoolDown-=1;
    }

    @Override
    protected void levelUp() {
        this.remainingCoolDown=0;
        this.healthPool+=(5*this.level);
        this.attackPoints+=(2*this.level);
        this.defencePoints+=this.level;
    }

    @Override
    public void castAbility() {
        this.remainingCoolDown=this.abilityCoolDown;
        this.healthAmount=Math.min(this.healthAmount+(10*this.defencePoints), this.healthPool);
        //randomly choose one enemy with range <3...
    }

    @Override
    public void death() {

    }
}
