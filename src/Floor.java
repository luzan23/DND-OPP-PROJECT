public class Floor extends Cell {
    private Occupant occupant;

    public Floor(Position p) {
        super(p, '.');
        this.occupant = null;
    }

    @Override
    public Occupant getOccupant() {
        return occupant;
    }

    @Override
    public void setOccupant(Occupant o) {
        this.occupant = o;
    }

    @Override
    public void accept(CellVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        if (occupant != null) {
            return occupant.toString();
        }
        return ".";
    }
}
