public class Hunter extends Player implements HeroicUnit{

    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(int range){
        this.range=range;
        this.ticksCount=0;

    }
    @Override
    public void castAbility() {

    }

    @Override
    public void onTick() {

    }

    @Override
    protected void levelUp() {
        this.arrowsCount += (10*this.level);
        this.attackPoints+=(2*this.level);
        this.defencePoints+=this.level;
    }


    @Override
    public void accept(Occupant o) {

    }

    @Override
    public void death() {

    }
}
