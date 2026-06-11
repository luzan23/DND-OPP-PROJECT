public class Hunter extends Player implements HeroicUnit{

    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(int range){
        this.range=range;
        this.arrowsCount=10*this.level;
        this.ticksCount=0;

    }
    @Override
    public void castAbility() {
        arrowsCount-=1;
        //deal damage
    }

    @Override
    public void onTick() {
        if(ticksCount==10) {
            this.arrowsCount += this.level;
            ticksCount = 0;
        }
        else ticksCount+=1;

    }

    @Override
    protected void levelUp() {
        this.arrowsCount+=(10*this.level);
        this.attackPoints+=(2*this.level);
        this.defencePoints+=this.level;
    }

    @Override
    public void death() {

    }

}
