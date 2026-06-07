public abstract  class Cell {
    protected Position position;

    public Position getPosition(){
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public abstract void accept(CellVisitor v);
    public abstract String toString();
}
