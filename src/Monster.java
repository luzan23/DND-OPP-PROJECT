public class Monster extends Enemy{

    protected int visionRange;

    public Monster(int vR,String name, int healthPool, int healthAmount, int attackPoints, int defencePoints) {
        super(name, healthPool, healthAmount, attackPoints, defencePoints);
        this.visionRange=vR;
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
