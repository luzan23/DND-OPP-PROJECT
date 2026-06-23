public class Trap extends Enemy{

    protected int visibilityTime;
    protected int invisibilityTime;
    protected int ticksCount;
    protected boolean visible;
    public Trap(int vTime, int invTime, String name, int healthPool, int healthAmount, int attackPoints, int defencePoints) {
        super(name, healthPool, healthAmount, attackPoints, defencePoints);
        this.visibilityTime=vTime;
        this.invisibilityTime=invTime;
        this.ticksCount=0;
        this.visible=true;
    }

    @Override
    public void accept(OccupantVisitor v) {

    }

    @Override
    public void death(Player p) {

    }

    @Override
    public void onEnemyTurn() {

    }
}
