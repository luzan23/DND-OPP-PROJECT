public abstract class Cell {
    protected Position position;
    protected char title;

    public Cell(Position position, char title) {
        this.position = position;
        this.title = title;
    }

    public Position getPosition() {
        return position;
    }

    public Occupant getOccupant() {
        return null;
    }

    public void setOccupant(Occupant o) {
    }

    public abstract void accept(CellVisitor v);

    @Override
    public String toString() {
        return String.valueOf(title);
    }
}
