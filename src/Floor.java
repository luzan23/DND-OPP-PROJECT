public class Floor extends Cell {
    private Occupant occupant;

    public Floor(Position position) {
        super(position);
        this.occupant = null;
    }

    public Occupant getOccupant(){
        return occupant;
    }

    public void setOccupant(Occupant o){
        this.occupant = o;
    }

    @Override
    public void accept(CellVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        if (occupant != null)
            return occupant.toString();
        return ".";
    }
}