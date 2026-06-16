public class Floor extends Cell
{
    private Occupant occupant;

    public Floor(Position p){
        super(p, '.');
        this.occupant=null;

    }
    @Override
    public Occupant getOccupant(){
        return this.occupant;
    }

    @Override
    public void setOccupant(Occupant o){
        this.occupant=o;
    }

    @Override
    public void accept(CellVisitor v) {
        v.visit(this);

    }

    @Override
    public String toString() {
        if (this.occupant != null) {
            return this.occupant.toString();
        }
        return super.toString();
    }
}
