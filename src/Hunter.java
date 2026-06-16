public class Hunter extends Player implements HeroicUnit{

    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(int range, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints,int abilityCoolDown){
        super(name, healthPool, healthAmount, attackPoints, defensePoints);
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
        super.levelUp();
        this.arrowsCount+=(10*this.level);
        this.attackPoints+=(2*this.level);
        this.defencePoints+=this.level;
    }


    @Override
    public void death(Player p) {

    }
}
