public class Warrior extends Player implements HeroicUnit{

    private int abilityCoolDown;
    private int remainingCoolDown;

    public Warrior(int abilityCoolDown){
        this.abilityCoolDown=abilityCoolDown;
        this.remainingCoolDown=0;
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

    @Override
    public void castAbility() {

    }

    @Override
    public void death() {

    }
}
