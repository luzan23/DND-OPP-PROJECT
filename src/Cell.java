public abstract  class Cell  {
    protected Position position;
    protected char title;

    public Cell(Position p, char title){
        this.position=p;
        this.title=title;
    }

    public Position getPosition(){
        return this.position;
    }

    public Occupant getOccupant(){
        return null;
    }

    public void setOccupant(Occupant o){
        //don't do anything, forwarded to children of the class during runtime - overrides
    }

    public String toString(){
        return String.valueOf(title);
    }

    public abstract void accept(CellVisitor v);
}
