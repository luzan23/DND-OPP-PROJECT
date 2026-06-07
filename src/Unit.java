public abstract class Unit implements Occupant, CellVisitor, OccupantVisitor {
    protected String name;
    protected int healthPool;
    protected int healthAmount;
    protected int attackPoints;
    protected int defencePoints;

    public void attack(Unit defender){

    }
    public int defend(){
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public abstract void death();


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
