public abstract class Unit implements Occupant, CellVisitor, OccupantVisitor {
    protected String name;
    protected int healthPool;
    protected int healthAmount;
    protected int attackPoints;
    protected int defencePoints;

    public Unit (String name, int healthPool, int healthAmount, int attackPoints, int defencePoints){
        this.name = name;
        this.healthPool=healthPool;
        this.healthAmount=healthAmount;
        this.attackPoints=attackPoints;
        this.defencePoints=defencePoints;
    }
    public abstract void accept(OccupantVisitor v);
    public abstract void death(Player p);
    //the player is who killed the enemy, so it's easier to give it the exp points when the enemy dies

    public void attack(Unit defender){

    }
    public int defend(){
        throw new UnsupportedOperationException("Not implemented yet!");
    }


    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position p) {

    }

    @Override
    public void visit(Wall w) {

    }

    @Override
    public void visit(Enemy e) {


    }

    @Override
    public void visit(Floor f) {
        Occupant occ = f.getOccupant();
        if(occ == null){
            this.setPosition(f.getPosition());
            f.setOccupant(this);
        }
        else {
            occ.accept(this);
        }
    }

    @Override
    public void visit(Player p) {

    }
}
