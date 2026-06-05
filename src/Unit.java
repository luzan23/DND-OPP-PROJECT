public abstract class Unit implements Occupant, CellVisitor, OccupantVisitor {
    public String name;
    private int healthPool;
    private int healthAmount;
    private int attackPoints;
    private int defencepoints;

    public void attack(Unit defender){

    }
    public int defend(){
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    public void death(){

    }


    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition() {

    }

    @Override
    public void visit(Wall w) {

    }

    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void visit(Floor f) {

    }

    @Override
    public void visit(Player p) {

    }
}
