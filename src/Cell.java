public abstract class Cell {
    protected Position position;

    protected Cell(Position position) {
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }

    public abstract void accept(CellVisitor v);
}
